package com.example.ecommere.repository;


import com.example.ecommere.model.Category;
import com.example.ecommere.model.Discount;
import com.example.ecommere.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

//    @Test
//    public void testCreateOneProductSuccess() {
//        Category category = new Category(1L, "Fruit");
//        Discount discount = new Discount(1L, "Giam 50");
//        Product product = new Product();
//        product.setProductName("Apple");
//        product.setProductPrice(5.5);
//        product.setProductDesc("This is an apple");
//        product.setCategory(category);
//        product.setDiscount(discount);
//        product.setQuantity(10L);
//        product.setCreatedAt(LocalDateTime.now());
//        product.setModifiedAt(LocalDateTime.now());
//        product.setDeleted(false);
//        assertNotNull(productRepository.save(product));
//    }
}
