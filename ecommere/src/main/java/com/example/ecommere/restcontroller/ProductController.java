package com.example.ecommere.restcontroller;


import com.example.ecommere.model.Product;
import com.example.ecommere.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/products")
    public List<Product> getListProducts() {
        return productService.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(value = "id") Long productId) {
        Product product = productService.get(productId);
        return product;
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody Product newProduct) {
        log.info(String.valueOf(newProduct));
        productService.create(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) {
        productService.delete(productId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable(value = "id") Long productId) {
        return productService.update(newProduct, productId);
    }
}
