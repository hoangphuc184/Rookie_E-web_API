package com.example.ecommere.service.impl;

import com.example.ecommere.model.Category;
import com.example.ecommere.repository.CategoryRepository;
import com.example.ecommere.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> retrieveCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        return  optCategory.get();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
