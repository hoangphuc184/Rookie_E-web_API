package com.example.ecommere.service;

import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAll(int page, int limit) throws DataNotFoundException;

    public Category get(Long categoryId) throws DataNotFoundException;

    public Category create(Category category) throws CreateDataFailException;

    public Category delete(Long categoryId) throws DeleteDataFailException;

    public Category update(Category newCategory, Long categoryId) throws DataNotFoundException;
}
