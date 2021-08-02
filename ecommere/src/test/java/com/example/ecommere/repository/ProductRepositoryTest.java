package com.example.ecommere.repository;


import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Discount;
import com.example.ecommere.model.Image;
import com.example.ecommere.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Test
    public void testGetAllProduct() {
        List<Product> products = productRepository.findAll();
        assertNotNull(products);
    }

    @Test
    public void testGetAllProductByCategory() {
        List<Product> products = productRepository.findProductByCategory(1L);
        assertNotNull(products);
    }

    @Test
    public void testGetProductById() throws DataNotFoundException {
        Product product;
        Optional<Product> result = productRepository.findById(1L);
        if (result.isPresent()) {
            product = result.get();
        } else throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        assertNotNull(product);
    }

    @Test
    public void testCreateProduct() throws DataNotFoundException {
        Product product = new Product();
        product.setName("Test product 1");
        product.setDesc("Test product 1");
        product.setPrice(4D);
        product.setQuantity(100L);

        Optional<Category> optCate = categoryRepository.findById(1L);
        Category category;
        if (optCate.isPresent()) {
            category = optCate.get();
        } else throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        product.setCategory(category);


        Optional<Discount> optDis = discountRepository.findById(1L);
        Discount discount;
        if (optDis.isPresent()) {
            discount = optDis.get();
        } else throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);
        product.setDiscount(discount);

        List<Image> images = new ArrayList<>();
        Image image1 = new Image();
        image1.setUrl("test i 1");
        Image image2 = new Image();
        image2.setUrl("test i 2");
        product.setImages(images);

        assertNotNull(productRepository.save(product));
    }

    @Test
    public void testUpdateProduct() throws DataNotFoundException {
        Optional<Product> optPro = productRepository.findById(18L);
        Product product;
        if (optPro.isPresent()) {
            product = optPro.get();
        } else throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);

        product.setName("New test product 1");

        assertNotNull(productRepository.save(product));
    }

    @Test
    public void testDeleteProduct() throws DataNotFoundException {
        Optional<Product> optPro = productRepository.findById(18L);
        Product product;
        if (optPro.isPresent()) {
            product = optPro.get();
        } else throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);

        product.setIsDeleted(true);
        productRepository.save(product);
        Optional<Product> actual = productRepository.findById(18L);
        assertTrue(actual.get().getIsDeleted());
    }

}
