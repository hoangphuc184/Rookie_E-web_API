package com.example.ecommere.service;

import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Product;


import java.util.List;

public interface ProductService {

    public List<Product> getAll(int page, int limit) throws DataNotFoundException;

    public List<Product> getAllByCategory(Long categoryId) throws DataNotFoundException;

    public Product get(Long productId) throws DataNotFoundException;

    public Product create(Product product) throws CreateDataFailException;

    public Product delete(Long productId) throws DeleteDataFailException;

    public Product update(Product newProduct, Long productId) throws DataNotFoundException;
}
