package com.example.ecommere.restcontroller;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.CategoryConverter;
import com.example.ecommere.dto.CategoryDTO;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getListCategories() {
        return categoryService.getAll();
    }

    @Autowired
    CategoryConverter categoryConverter;

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable(value = "id") Long categoryId) throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        CategoryDTO category = categoryConverter.convertToDTO(categoryService.get(categoryId));
        response.setData(category);
        response.setSuccessCode(SuccessCode.DATA_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/categories")
    public void createCategory(@RequestBody Category newCategory) {
        log.info(String.valueOf(newCategory));
        categoryService.create(newCategory);
    }

    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId) {
        categoryService.delete(categoryId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@RequestBody Category newCategory, @PathVariable(value = "id") Long categoryId) {
        return categoryService.update(newCategory, categoryId);
    }

}