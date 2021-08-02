package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.UserAddressDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAddressConverter {

    @Autowired
    ModelMapper modelMapper;

    public UserAddressDTO convertToDTO(UserAddress userAddress) throws ParseEntityDtoException {
        try {
            return modelMapper.map(userAddress, UserAddressDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public UserAddress convertToEntity(UserAddressDTO userAddressDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(userAddressDTO, UserAddress.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
