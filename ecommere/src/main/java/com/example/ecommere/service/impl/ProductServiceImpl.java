package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Image;
import com.example.ecommere.model.Product;
import com.example.ecommere.repository.ProductRepository;
import com.example.ecommere.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() throws DataNotFoundException {
        try {
            log.info("Got product list");
            return productRepository.findAll();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
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
            return productRepository.findById(productId)
                    .map(product -> {
                        product.setName(newProduct.getName());
                        product.setDesc(newProduct.getDesc());
                        product.setPrice(newProduct.getPrice());
                        product.setQuantity(newProduct.getQuantity());
                        product.setDiscount(newProduct.getDiscount());
                        product.setCategory(newProduct.getCategory());
                        product.setImages(newProduct.getImages());
                        product.setModifiedAt(newProduct.getModifiedAt());
                        return productRepository.save(product);
                    }).orElseGet(() -> {
                        newProduct.setId(productId);
                        return productRepository.save(newProduct);
                    });
        } catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }
}
