package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.CartDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Carts;
import com.example.ecommere.model.User;
import com.example.ecommere.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    public CartDTO convertToDTO(Carts carts) throws ParseEntityDtoException {
        try {
            CartDTO cartDTO = modelMapper.map(carts, CartDTO.class);
            cartDTO.setUser_id(carts.getUser().getId());
            return cartDTO;
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public Carts convertToEntity(CartDTO cartDTO) throws ParseEntityDtoException {
        try {
            Carts carts = modelMapper.map(cartDTO, Carts.class);
            User user = userRepository.findById(cartDTO.getUser_id()).get();
            carts.setUser(user);
            return carts;
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
