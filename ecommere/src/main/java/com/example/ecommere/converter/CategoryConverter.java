package com.example.ecommere.converter;

import com.example.ecommere.dto.CategoryDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    ModelMapper modelMapper;

    public CategoryDTO convertToDTO(Category category) throws ParseEntityDtoException {
        try{
            CategoryDTO dto = modelMapper.map(category , CategoryDTO.class);
            dto.setImage(category.getImage().getUrl());
            return dto;
        }catch(Exception ex){
            throw new ParseEntityDtoException(null);
        }
    }
}
