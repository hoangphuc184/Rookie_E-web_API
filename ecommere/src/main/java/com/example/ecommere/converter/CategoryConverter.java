package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.CategoryDTO;
import com.example.ecommere.dto.ImageDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Image;
import com.example.ecommere.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ImageRepository imageRepository;

    public CategoryDTO convertToDTO(Category category) throws ParseEntityDtoException {
//        try{
//        }catch(Exception ex){
//            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
//        }
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setImage_url(category.getImage().getUrl());
        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) throws ParseEntityDtoException {
        try {
            Category category = modelMapper.map(categoryDTO, Category.class);
            Image image = new Image();
            image.setUrl(categoryDTO.getImage_url());
            category.setImage(image);
            return category;
        }catch (Exception ex){
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
