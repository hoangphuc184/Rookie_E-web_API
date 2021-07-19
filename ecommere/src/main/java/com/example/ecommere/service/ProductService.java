package com.example.ecommere.service;

import com.example.ecommere.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAll();

    public Product get(Long productId);

    public Product create(Product product);

    public void delete(Long productId);

    public Product update(Product newProduct, Long productId);
}
