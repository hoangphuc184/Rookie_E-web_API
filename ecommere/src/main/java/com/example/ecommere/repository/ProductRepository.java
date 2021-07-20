package com.example.ecommere.repository;

import com.example.ecommere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

}
