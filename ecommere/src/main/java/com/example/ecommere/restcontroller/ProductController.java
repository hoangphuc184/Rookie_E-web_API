package com.example.ecommere.restcontroller;


import com.example.ecommere.exception.ProductException;
import com.example.ecommere.model.Product;
import com.example.ecommere.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping
    public List<Product> getListProducts() {
        return productService.retrieveProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            throw new ProductException(productId);
        }
        return product;
    }

    @PostMapping
    public void createProduct(Product product) {
        if (product == null) {
            throw new ProductException(product);
        }
        productService.saveProduct(product);
    }
}
