package blog.dao;

import blog.dao.mapper.ArticleMapper;
import blog.dao.mapper.CommentMapper;
import blog.dao.mapper.ListMapper;
import blog.dao.mapper.MapCategoryMapper;
import blog.entity.Account;
import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Comment;
import blog.form.CommentForm;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SQLDAO {
    private final QueryRunner sql = new QueryRunner();

    public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
        return sql.query(c, "select * from category;", new MapCategoryMapper());
    }
    public List<Article> listArticles(Connection c, int offset, int limit) throws SQLException {
        return sql.query(c, "select * from article a order by a.id desc limit ? offset ?", new ListMapper<>(new ArticleMapper()), limit, offset);
    }

    public int countArticles(Connection c) throws SQLException {
        return sql.query(c, "select count(*) from article", new ScalarHandler<Number>()).intValue();
    }
    public List<Article> listArticlesByCategory(Connection c, String categoryUrl, int offset, int limit) throws SQLException {
        return sql.query(c, "select a.* from article a, category c where c.id=a.id_category and c.url=? order by a.id desc limit ? offset ?",
                new ListMapper<>(new ArticleMapper()), categoryUrl.substring(24), limit, offset);
    }

    public int countArticlesByCategory(Connection c, String categoryUrl) throws SQLException {
        return sql.query(c, "select count(a.id) from article a, category c where a.id_category=c.id and c.url=?", new ScalarHandler<Number>(), categoryUrl).intValue();
    }

    public Category findCategoryByUrl(Connection c, String categoryUrl) throws SQLException {
        return sql.query(c, "select * from category  where category.url = ?", new BeanHandler<>(Category.class), categoryUrl.substring(24));
    }
    public List<Article> listArticlesBySearchQuery(Connection c, String searchQuery, int offset, int limit) throws SQLException {
        String q = "%" + searchQuery + "%";
        return sql.query(c, "select * from article a where (a.title like ? or a.content like ?)  order by a.id desc limit ? offset ?",
                new ListMapper<>(new ArticleMapper()), q, q, limit, offset);
    }

    public int countArticlesBySearchQuery(Connection c, String searchQuery) throws SQLException {
        String q = "%" + searchQuery + "%";
        return new QueryRunner().query(c, "select count(a.id) from article a  where (a.title like ? or a.content like ?)",
                new ScalarHandler<Number>(), q, q).intValue();
    }
    public Article findArticleById(Connection c, long idArticle) throws SQLException {
        return sql.query(c, "select * from article a where a.id = ?", new ArticleMapper(), idArticle);
    }

    public void updateArticleViews(Connection c, Article article) throws SQLException {
        sql.update(c, "update article set views=? where id=?", article.getViews(), article.getId());
    }

    public List<Comment> listComments(Connection c, int id_article, int offset, int limit) throws SQLException {
        return sql.query(c,
                "select c.*, a.name, a.email, a.created as accountCreated, a.avatar from comment c, account a " +
                        "where a.id = c.id_account and c.id_article = ? order by c.id desc limit ? offset ?",
                new ListMapper<>(new CommentMapper(true)), id_article, limit, offset);
    }

    public Article findArticleForNewCommentNotification(Connection c, long id) throws SQLException {
        return sql.query(c, "select a.id, a.id_category, a.url, a.title from article a where a.id = ?", new ArticleMapper(), id);
    }

    public Account findAccountByEmail(Connection c, String email) throws SQLException {
        return sql.query(c, "select * from account a where a.email = ?", new BeanHandler<>(Account.class), email);
    }

    public int countComments(Connection c, long id) throws SQLException {
        return sql.query(c, "select count(*) from comment where id_article=?", new ScalarHandler<Number>(), id).intValue();
    }

    public void updateArticleComments(Connection c, Article article) throws SQLException {
        sql.update(c, "update article set comments=? where id=?", article.getComments(), article.getId());
    }


    public Account createNewAccount(Connection c, String email, String name, String avatar) throws SQLException {
        return sql.insert(c, "insert account(email,name,avatar) values( ? , ? , ? )",
                new BeanHandler<>(Account.class), email, name, avatar);
    }

    public Comment createComment(Connection c, CommentForm form, int idAccount) throws SQLException {
        return sql.insert(c, "insert comment(id_article,id_account,content) values( ? , ? , ? )",
                new CommentMapper(false,false), form.getIdArticle(), idAccount, "<p>"+form.getContent()+"</p>");
    }

    public int id_account(Connection c) throws SQLException {
        return sql.query(c, "select max(id) from account;",new ScalarHandler<Number>()).intValue() ;
    }


}
