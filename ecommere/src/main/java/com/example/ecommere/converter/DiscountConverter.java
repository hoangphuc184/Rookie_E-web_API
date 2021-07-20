package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.DiscountDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Discount;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DiscountConverter {

    @Autowired
    ModelMapper modelMapper;

    public DiscountDTO convertToDTO(Discount discount) throws ParseEntityDtoException {
        try {
            return modelMapper.map(discount, DiscountDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public Discount convertToEntity(DiscountDTO discountDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(discountDTO, Discount.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
