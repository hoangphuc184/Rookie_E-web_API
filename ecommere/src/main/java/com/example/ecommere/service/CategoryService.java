package com.example.ecommere.service;

import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll();

    public Category get(Long categoryId) throws DataNotFoundException;

    public Category create(Category category);

    public void delete(Long categoryId);

    public Category update(Category newCategory, Long categoryId);
}
