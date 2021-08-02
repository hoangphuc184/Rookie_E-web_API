package com.example.ecommere.service;

import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Carts;

import java.util.List;

public interface CartService {

    public List<Carts> getAll() throws DataNotFoundException;

    public Carts get(Long cartId) throws DataNotFoundException;

    public Carts create(Carts carts) throws CreateDataFailException;

    public Carts delete(Long cartId) throws DeleteDataFailException;

    public Carts update(Carts newCarts, Long cartId) throws DataNotFoundException;
}
