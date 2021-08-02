package com.example.ecommere.restcontroller.admin;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryConverter categoryConverter;

    @GetMapping("/categories")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<ResponseDTO> getListCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "limit", defaultValue = "10") int limit)
            throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categories = categoryService.getAll(page, limit);
        if (categories == null) {
            response.setErrorCode(ErrorCode.LIST_CATE_NOT_FOUND_EXCEPTION);
        }else {
            for (Category category : categories) {
                CategoryDTO categoryDTO = categoryConverter.convertToDTO(category);
                categoryDTOList.add(categoryDTO);
            }
            response.setData(categoryDTOList);
            response.setSuccessCode(SuccessCode.LIST_CATE_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable(value = "id") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryService.get(categoryId);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(category);
        if (categoryDTO == null) {
            response.setErrorCode(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }else {
            response.setData(categoryDTO);
            response.setSuccessCode(SuccessCode.CATEGORY_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> createCategory(@Valid @RequestBody CategoryDTO newCategoryDTO)
            throws CreateDataFailException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(newCategoryDTO);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(categoryService.create(category));
        if (categoryDTO == null) {
            response.setErrorCode(ErrorCode.CATEGORY_CREATED_FAIL_EXCEPTION);
        }else {
            response.setData(categoryDTO);
            response.setSuccessCode(SuccessCode.CATEGORY_CREATED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable(value = "id") Long categoryId)
            throws ParseEntityDtoException, DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryService.delete(categoryId);
        if (!category.getIsDeleted()) {
            response.setErrorCode(ErrorCode.CATEGORY_DELETED_FAIL_EXCEPTION);
        }else {
            CategoryDTO categoryDTO = categoryConverter.convertToDTO(category);
            response.setData(categoryDTO);
            response.setSuccessCode(SuccessCode.CATEGORY_DELETED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO newCategoryDTO, @PathVariable(value = "id") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(newCategoryDTO);
        CategoryDTO categoryDTO = categoryConverter.convertToDTO(categoryService.update(category, categoryId));
        if (categoryDTO == null) {
            response.setErrorCode(ErrorCode.CATEGORY_UPDATED_FAIL_EXCEPTION);
        }else {
            response.setData(categoryDTO);
            response.setSuccessCode(SuccessCode.CATEGORY_UPDATED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

}