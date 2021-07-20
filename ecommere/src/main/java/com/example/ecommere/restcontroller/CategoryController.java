package com.example.ecommere.restcontroller;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.CategoryConverter;
import com.example.ecommere.dto.CategoryDTO;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryConverter categoryConverter;

    @GetMapping("/categories")
    public ResponseEntity<ResponseDTO> getListCategories() throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryService.getAll()) {
            CategoryDTO categoryDTO = categoryConverter.convertToDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        response.setData(categoryDTOList);
        response.setSuccessCode(SuccessCode.LIST_CATE_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable(value = "id") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(categoryService.get(categoryId));
        response.setData(categoryDTO);
        response.setSuccessCode(SuccessCode.CATEGORY_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CategoryDTO newCategoryDTO)
            throws CreateDataFailException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(newCategoryDTO);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(categoryService.create(category));
        response.setData(categoryDTO);
        response.setSuccessCode(SuccessCode.CATEGORY_CREATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable(value = "id") Long categoryId)
            throws ParseEntityDtoException, DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryService.delete(categoryId);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(category);
        response.setData(categoryDTO);
        response.setSuccessCode(SuccessCode.CATEGORY_DELETED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody CategoryDTO newCategoryDTO, @PathVariable(value = "id") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(newCategoryDTO);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(categoryService.update(category, categoryId));
        response.setData(categoryDTO);
        response.setSuccessCode(SuccessCode.CATEGORY_UPDATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

}