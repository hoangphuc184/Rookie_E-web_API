package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.PaymentListDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentListConverter {

    @Autowired
    ModelMapper modelMapper;

    public PaymentListDTO convertToDTO(User user) throws ParseEntityDtoException {
        try {
            return modelMapper.map(user, PaymentListDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }
    public User convertToEntity(PaymentListDTO paymentListDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(paymentListDTO, User.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
