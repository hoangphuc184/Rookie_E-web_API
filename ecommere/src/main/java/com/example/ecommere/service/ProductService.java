package com.example.ecommere.service;

import com.example.ecommere.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> retrieveProducts();

    public Product getProduct(Long productId);

    public Product saveProduct(Product product);
}
