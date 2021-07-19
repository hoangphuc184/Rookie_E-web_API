package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.Category;
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

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category get(Long categoryId) throws DataNotFoundException {
        try{
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            log.info("Found Category: " + optCategory.get().getName());
            return optCategory.get();
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND_EXCEPTON);
        }

    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long categoryId) {
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
//       optCategory.ifPresent(categoryRepository::delete);
        optCategory.map(category -> {
            category.setIsDeleted(true);
            return categoryRepository.save(category);
        });
        //log.info("Category deleted " + optCategory.get().getId());
    }

    public Category update(Category newCategory, Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setDesc(newCategory.getDesc());
//                    category.setImage(newCategory.getImage());
//                    category.setProducts(newCategory.getProducts());
                    category.setModifiedAt(newCategory.getModifiedAt());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(categoryId);
                    return categoryRepository.save(newCategory);
                });
    }
}
