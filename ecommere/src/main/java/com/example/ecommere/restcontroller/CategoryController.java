package com.example.ecommere.restcontroller;

import com.example.ecommere.exception.CategoryException;
import com.example.ecommere.model.Category;
import com.example.ecommere.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getListCategories() {
        return categoryService.retrieveCategory();
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            throw new CategoryException(categoryId);
        }
        return category;
    }

    @PostMapping
    public void saveCategory(@RequestBody Category newCategory) {
        System.out.println(newCategory);
        if (newCategory == null) {
            throw new CategoryException(newCategory);
        }
        categoryService.saveCategory(newCategory);
    }
}