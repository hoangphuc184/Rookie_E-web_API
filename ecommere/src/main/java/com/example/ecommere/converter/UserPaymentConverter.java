package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.UserPaymentDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.UserPayment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentConverter {

    @Autowired
    ModelMapper modelMapper;

    public UserPaymentDTO convertToDTO(UserPayment userPayment) throws ParseEntityDtoException {
        try {
            return modelMapper.map(userPayment, UserPaymentDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public UserPayment convertToEntity(UserPaymentDTO userPaymentDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(userPaymentDTO, UserPayment.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
