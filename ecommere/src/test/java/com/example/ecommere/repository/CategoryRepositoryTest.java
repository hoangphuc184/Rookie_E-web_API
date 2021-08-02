package com.example.ecommere.repository;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.Category;

import com.example.ecommere.model.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
    }

    @Test
    public void testGetCategoryById() throws DataNotFoundException {
        Category category;
        Optional<Category> result = categoryRepository.findById(1L);
        if (result.isPresent()){
            category = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
        assertNotNull(category);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        Image image = new Image();
        image.setUrl("Test cate 1");
        category.setName("Test cate 1");
        category.setDesc("Test cate 1");
        category.setImage(image);
        assertNotNull(categoryRepository.save(category));
    }

    @Test
    public void testUpdateCategory() throws DataNotFoundException {
        Optional<Category> result = categoryRepository.findById(4L);
        Category category;
        if (result.isPresent()) {
            category = result.get();
        }else {
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
        category.setName("New Test cate 1");
        assertNotNull(categoryRepository.save(category));
    }

    @Test
    public void testDeleteCategory() throws DataNotFoundException {
        Optional<Category> result = categoryRepository.findById(4L);
        Category category;
        if (result.isPresent()) {
            category = result.get();
        }else {
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }
        category.setIsDeleted(true);
        categoryRepository.save(category);
        Optional<Category> actual = categoryRepository.findById(4L);
        assertTrue(actual.get().getIsDeleted());
    }

}
