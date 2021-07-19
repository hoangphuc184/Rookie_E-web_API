package com.example.ecommere.repository;

import com.example.ecommere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

}
