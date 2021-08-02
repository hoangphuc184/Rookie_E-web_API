package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Carts;
import com.example.ecommere.repository.CartRepository;
import com.example.ecommere.service.CartService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Carts> getAll() throws DataNotFoundException {
        try {
            log.info("List carts loaded");
            return cartRepository.findAll();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_CART_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Carts get(Long cartId) throws DataNotFoundException {
        try {
            Optional<Carts> optCart = cartRepository.findById(cartId);
            log.info("Found Cart: " + optCart.get().getId());
            return optCart.get();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.CART_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Carts create(Carts carts) throws CreateDataFailException {
        try {
            log.info("Cart created: " + carts.getId());
            return cartRepository.save(carts);
        }catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.CART_CREATED_FAIL_EXCEPTION);
        }
    }

    @Override
    public Carts delete(Long cartId) throws DeleteDataFailException {
        try {
            Optional<Carts> optCart = cartRepository.findById(cartId);
            Carts cart = optCart.get();
            cart.setIsDeleted(true);
            return cart;
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.CART_DELETED_FAIL_EXCEPTION);
        }
    }

    @Override
    public Carts update(Carts newCarts, Long cartId) throws DataNotFoundException {
        try {
            newCarts.setId(cartId);
            return cartRepository.save(newCarts);

        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.CART_NOT_FOUND_EXCEPTION);
        }
    }
}
