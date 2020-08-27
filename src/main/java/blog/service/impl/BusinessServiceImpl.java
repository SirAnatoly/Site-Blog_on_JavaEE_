package blog.service.impl;

import blog.dao.SQLDAO;
import blog.entity.Account;
import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Comment;
import blog.exception.ApplicationException;
import blog.exception.RedirectToValidUrlException;
import blog.form.CommentForm;
import blog.form.ContactForm;
import blog.model.Items;
import blog.model.SocialAccount;
import blog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BusinessServiceImpl implements BusinessService{
    private final String appHost;
    private final DataSource dataSource;
    private final SQLDAO sql;
    private final SocialService socialService;
    private final AvatarService avatarService;
    private final NotificationService notificationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

    BusinessServiceImpl(ServiceManager serviceManager) {
        super();
        this.dataSource = serviceManager.dataSource;
        this.sql = new SQLDAO();
        this.avatarService = serviceManager.avatarService;
        this.socialService = serviceManager.socialService;
        this.notificationService = serviceManager.notificationService;
        this.appHost = serviceManager.getApplicationProperty("app.host");
    }

    @Override
    public Map<Integer, Category> mapCategories() {
        try (Connection c = dataSource.getConnection()) {
            return sql.mapCategories(c);
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public Items<Article> listArticles(int offset, int limit) {
        try (Connection c = dataSource.getConnection()) {
            Items<Article> items = new Items<>();
            items.setItems(sql.listArticles(c, offset, limit));
            items.setCount(sql.countArticles(c));
            return items;
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit) {
        try (Connection c = dataSource.getConnection()) {
            Items<Article> items = new Items<>();
            items.setItems(sql.listArticlesByCategory(c, categoryUrl, offset, limit));
            items.setCount(sql.countArticlesByCategory(c, categoryUrl));
            return items;
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public Category findCategoryByUrl(String categoryUrl) {
        try (Connection c = dataSource.getConnection()) {
            return sql.findCategoryByUrl(c, categoryUrl);
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }
    @Override
    public Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit) {
        try (Connection c = dataSource.getConnection()) {
            Items<Article> items = new Items<>();
            items.setItems(sql.listArticlesBySearchQuery(c, searchQuery, offset, limit));
            items.setCount(sql.countArticlesBySearchQuery(c, searchQuery));
            return items;
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public Article viewArticle(Integer idArticle, String requestUrl) throws RedirectToValidUrlException {

        try (Connection c = dataSource.getConnection()) {
            Article article = sql.findArticleById(c, idArticle);
            if (article == null) {
                return null;
            }
            if (!article.getArticleLink().substring(24).equals(requestUrl.replace('%',' '))) {

                throw new RedirectToValidUrlException(article.getArticleLink());
            } else {
                article.setViews(article.getViews() + 1);
                sql.updateArticleViews(c, article);
                c.commit();
                return article;
            }
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> listComments(int id_article, int offset, int limit) {
            try (Connection c = dataSource.getConnection()) {
                return sql.listComments(c, id_article, offset, limit);
            } catch (SQLException e) {
                throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
            }
        }
    @Override
    public Comment createComment(CommentForm form) {

        String newAvatarPath = null;
        try (Connection c = dataSource.getConnection()) {
            SocialAccount socialAccount = socialService.getSocialAccount(form.getAuthToken());
            Account account = sql.findAccountByEmail(c, socialAccount.getEmail());
            if (account == null) {
                newAvatarPath = avatarService.downloadAvatar(socialAccount.getAvatar());
                account = sql.createNewAccount(c, socialAccount.getEmail(), socialAccount.getName(), newAvatarPath);
            }
            account.setId(sql.id_account(c));
            Comment comment = sql.createComment(c, form, account.getId());
            comment.setAccount(account);
            Article article = sql.findArticleForNewCommentNotification(c, form.getIdArticle());
            article.setComments(sql.countComments(c, article.getId()));
            sql.updateArticleComments(c, article);
            c.commit();
            sendNewCommentNotification(article, form.getContent());
            return comment;
        } catch (SQLException | RuntimeException | IOException e) {
            if(avatarService.deleteAvatarIfExists(newAvatarPath)){
                LOGGER.info("Avatar "+newAvatarPath+" deleted");
            }
            throw new ApplicationException("Can't create new comment: " + e.getMessage(), e);
        }
    }
    private void sendNewCommentNotification(Article article, String commentContent) {
        String title = article.getTitle();
        String content = commentContent;
        notificationService.sendNotification(title, content);
    }

    @Override
    public void createContactRequest(ContactForm form) {
        String content =  "Name: " + form.getName()+"\n"+"email: "+form.getEmail()+"\n"+"message: "+form.getText();
        notificationService.sendNotification("Notification from Blog", content);
    }

}
