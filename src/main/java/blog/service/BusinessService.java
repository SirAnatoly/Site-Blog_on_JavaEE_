package blog.service;

import blog.entity.Article;
import blog.entity.Category;
import blog.model.Items;

import java.util.Map;

public interface BusinessService {
    Map<Integer, Category> mapCategories();
    Items<Article> listArticles(int offset, int limit);
}
