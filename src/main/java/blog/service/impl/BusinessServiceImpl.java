package blog.service.impl;

import blog.dao.SQLDAO;
import blog.entity.Article;
import blog.entity.Category;
import blog.exception.ApplicationException;
import blog.exception.RedirectToValidUrlException;
import blog.model.Items;
import blog.service.BusinessService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class BusinessServiceImpl implements BusinessService{
    private final DataSource dataSource;
    private final SQLDAO sql;

    BusinessServiceImpl(ServiceManager serviceManager) {
        super();
        this.dataSource = serviceManager.dataSource;
        this.sql = new SQLDAO();
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
}
