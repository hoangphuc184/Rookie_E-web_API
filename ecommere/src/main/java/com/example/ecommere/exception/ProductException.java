package com.example.ecommere.exception;

import com.example.ecommere.model.Product;

public class ProductException extends RuntimeException{
    public ProductException(Long id) {
        super("Could not find product with id = " + id);
    }

    public ProductException(Product product) {
        super("Could not create new product");
    };
}
