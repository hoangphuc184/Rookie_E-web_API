package com.example.ecommere.service;

import com.example.ecommere.model.Carts;

import java.util.List;

public interface CartService {

    public List<Carts> getAll();

    public Carts get(Long cartId);

    public Carts create(Carts carts);

    public void delete(Long cartId);
}
