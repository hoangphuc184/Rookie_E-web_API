package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.AddressListDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressListConverter {

    @Autowired
    ModelMapper modelMapper;

    public AddressListDTO convertToDTO(User user) throws ParseEntityDtoException {
        try {
            return modelMapper.map(user, AddressListDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public User convertToEntity(AddressListDTO addressListDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(addressListDTO, User.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
