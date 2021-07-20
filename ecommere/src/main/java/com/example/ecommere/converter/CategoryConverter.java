package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.CategoryDTO;
import com.example.ecommere.dto.ImageDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Image;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CategoryConverter {

    @Autowired
    ModelMapper modelMapper;

    public CategoryDTO convertToDTO(Category category) throws ParseEntityDtoException {
        try{
            return modelMapper.map(category , CategoryDTO.class);
        }catch(Exception ex){
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public Category convertToEntity(CategoryDTO categoryDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(categoryDTO, Category.class);
        }catch (Exception ex){
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
