package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Image;
import com.example.ecommere.repository.CategoryRepository;
import com.example.ecommere.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() throws DataNotFoundException {
        try {
            log.info("Got Category list");
            return categoryRepository.findAll();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_CATE_NOT_FOUND_EXCEPTION);
        }

    }

    public Category get(Long categoryId) throws DataNotFoundException {
        try{
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            log.info("Found Category: " + optCategory.get().getName());
            return optCategory.get();
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
    }

    public Category create(Category category) throws CreateDataFailException {
        try {
            log.info("Saved Category: " + category.getName());
            return categoryRepository.save(category);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.CATEGORY_CREATED_FAIL_EXCEPTION);
        }
    }

    public Category delete(Long categoryId) throws DeleteDataFailException {
        try {
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            Category category = optCategory.get();
            Image image = category.getImage();
            image.setIsDeleted(true);
            category.setIsDeleted(true);
            category.setImage(image);
            log.info("Deleted Category: " + category.getName());
            return categoryRepository.save(category);
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.CATEGORY_DELETED_FAIL_EXCEPTION);
        }
    }

    public Category update(Category newCategory, Long categoryId) throws DataNotFoundException {
        try {

            return categoryRepository.findById(categoryId)
                    .map(category -> {
                        category.setName(newCategory.getName());
                        category.setDesc(newCategory.getDesc());
                        if (newCategory.getImage() != null) {
                            Image image = category.getImage();
                            image.setUrl(newCategory.getImage().getUrl());
                            image.setDesc(newCategory.getImage().getDesc());
                            category.setImage(image);
                        }
                        category.setModifiedAt(newCategory.getModifiedAt());
                        category.setIsDeleted(newCategory.getIsDeleted());
                        log.info("Updated category: " + category.getName());
                        return categoryRepository.save(category);
                    })
                    .orElseGet(() -> {
                        newCategory.setId(categoryId);
                        log.info("Create new category: " + newCategory.getName());
                        return categoryRepository.save(newCategory);
                    });
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
    }
}
