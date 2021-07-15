package com.example.ecommere.service.impl;

import com.example.ecommere.exception.ProductException;
import com.example.ecommere.model.Product;
import com.example.ecommere.repository.ProductRepository;
import com.example.ecommere.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> retrieveProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId);
        return  optProduct.get();
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new ProductException(productId));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
