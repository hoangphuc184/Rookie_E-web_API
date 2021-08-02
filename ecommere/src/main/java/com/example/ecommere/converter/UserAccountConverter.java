package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.UserAccountDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountConverter {

    @Autowired
    ModelMapper modelMapper;

    public UserAccountDTO convertToDTO(User user) throws ParseEntityDtoException {
        try {
            return modelMapper.map(user, UserAccountDTO.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public User convertToEntity(UserAccountDTO userAccountDTO) throws ParseEntityDtoException {
        try {
            return modelMapper.map(userAccountDTO, User.class);
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
