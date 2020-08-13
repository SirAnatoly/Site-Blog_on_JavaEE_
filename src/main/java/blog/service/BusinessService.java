package blog.service;

import blog.entity.Category;

import java.util.Map;

public interface BusinessService {
    Map<Integer, Category> mapCategories();
}
