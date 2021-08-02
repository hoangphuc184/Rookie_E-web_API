package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Image;
import com.example.ecommere.model.Product;
import com.example.ecommere.repository.ImageRepository;
import com.example.ecommere.repository.ProductRepository;
import com.example.ecommere.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(int page, int limit) throws DataNotFoundException {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<Product> productPage = productRepository.findAll(pageable);
            List<Product> products = productPage.getContent();
            log.info("Got product list");
            return products;
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }

    public List<Product> getAllByCategory(Long categoryId) throws DataNotFoundException {
        try {
            List<Product> products = productRepository.findProductByCategory(categoryId);
            log.info("Got product list with category = " + categoryId);
            return products;
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }

    public Product get(Long productId) throws DataNotFoundException {
        try {
            Optional<Product> optProduct = productRepository.findById(productId);
            log.info("Found product: " + optProduct.get().getName());
            return  optProduct.get();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }

    public Product create(Product product) throws CreateDataFailException {
        try {
            log.info("Created product: " + product.getName());
            return productRepository.save(product);
        }catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.PRODUCT_CREATED_FAIL_EXCEPTION);
        }
    }

    public Product delete(Long productId) throws DeleteDataFailException {
        try {
            Optional<Product> optProduct = productRepository.findById(productId);
            Product product = optProduct.get();
            List<Image> imageList = product.getImages();
            for (Image image : imageList) {
                image.setIsDeleted(true);
            };
            product.setImages(imageList);
            product.setIsDeleted(true);
            return productRepository.save(product);
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.PRODUCT_DELETED_FAIL_EXCEPTION);
        }
    }

    public Product update(Product newProduct, Long productId) throws DataNotFoundException {
        try {
            newProduct.setId(productId);
            return productRepository.save(newProduct);
        } catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }
}
