package com.example.ecommere.service;

import com.example.ecommere.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> retrieveCategory();

    public Category getCategory(Long categoryId);

    public Category saveCategory(Category category);
}
