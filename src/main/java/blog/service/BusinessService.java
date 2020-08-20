package blog.service;

import blog.entity.Article;
import blog.entity.Category;
import blog.exception.RedirectToValidUrlException;
import blog.model.Items;

import java.util.Map;

public interface BusinessService {

    Map<Integer, Category> mapCategories();

    Items<Article> listArticles(int offset, int limit);

    Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);

    Category findCategoryByUrl(String categoryUrl);

    Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit);

    Article viewArticle(Integer idArticle, String requestUrl) throws RedirectToValidUrlException;
}
