package com.example.ecommere.repository;

import com.example.ecommere.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where category_id = ?1", nativeQuery = true)
    public List<Product> findProductByCategory(Long categoryId);
}
