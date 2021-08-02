package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Image;
import com.example.ecommere.repository.CategoryRepository;
import com.example.ecommere.repository.ImageRepository;
import com.example.ecommere.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageRepository imageRepository;

    public List<Category> getAll(int page, int limit) throws DataNotFoundException {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<Category> categoryPage = categoryRepository.findAll(pageable);
            List<Category> categories = categoryPage.getContent();
            log.info("Got Category list");
            return categories;
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_CATE_NOT_FOUND_EXCEPTION);
        }

    }

    public Category get(Long categoryId) throws DataNotFoundException {
        try{
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            log.info("Found Category: " + optCategory.get().getId());
            return optCategory.get();
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
    }

    public Category create(Category category) throws CreateDataFailException {
        try {
            log.info("Saved Category: " + category.getId());
            return categoryRepository.save(category);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.CATEGORY_CREATED_FAIL_EXCEPTION);
        }
    }

    public Category delete(Long categoryId) throws DeleteDataFailException {
        try {
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            Category category = optCategory.get();
            category.setIsDeleted(true);
            log.info("Deleted Category: " + category.getId());
            return categoryRepository.save(category);
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.CATEGORY_DELETED_FAIL_EXCEPTION);
        }
    }

    public Category update(Category newCategory, Long categoryId) throws DataNotFoundException {
        try {
            newCategory.setId(categoryId);
            return categoryRepository.save(newCategory);
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
    }
}
