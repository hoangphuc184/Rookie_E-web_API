package com.example.ecommere.service.impl;

import com.example.ecommere.model.Product;
import com.example.ecommere.repository.ProductRepository;
import com.example.ecommere.service.ProductService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId);
        return  optProduct.orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId);
//        optProduct.ifPresent(productRepository::delete);
        optProduct.map(product -> {
            product.setIsDeleted(true);
            return productRepository.save(product);
        });
        log.info("product deleted " + optProduct.get().getId());
    }

    public Product update(Product newProduct, Long productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDesc(newProduct.getDesc());
                    product.setPrice(newProduct.getPrice());
                    product.setQuantity(newProduct.getQuantity());
                    product.setModifiedAt(newProduct.getModifiedAt());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(productId);
                    return productRepository.save(newProduct);
                });
    }
}
