package com.example.ecommere.service.impl;

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

    public List<Carts> getAll() {
        return cartRepository.findAll();
    }

    public Carts get(Long cartId) {
        Optional<Carts> optCart = cartRepository.findById(cartId);
        return optCart.orElse(null);
    }

    public Carts create(Carts carts) {
        return cartRepository.save(carts);
    }

    public void delete(Long cartId)  {
        Optional<Carts> optCart = cartRepository.findById(cartId);
        optCart.map(carts -> {
            carts.setIsDeleted(true);
            return cartRepository.save(carts);
        });
        log.info("Cart deleted " + optCart.get().getId());
    }
}
