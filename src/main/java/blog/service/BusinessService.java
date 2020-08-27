package blog.service;

import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Comment;
import blog.exception.RedirectToValidUrlException;
import blog.exception.ValidateException;
import blog.form.CommentForm;
import blog.form.ContactForm;
import blog.model.Items;

import java.util.List;
import java.util.Map;

public interface BusinessService {

    Map<Integer, Category> mapCategories();

    Items<Article> listArticles(int offset, int limit);

    Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);

    Category findCategoryByUrl(String categoryUrl);

    Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit);

    Article viewArticle(Integer idArticle, String requestUrl) throws RedirectToValidUrlException;

    List<Comment> listComments(int id_article, int offset, int limit);

    Comment createComment(CommentForm form);

    void createContactRequest(ContactForm form) throws ValidateException;

}
