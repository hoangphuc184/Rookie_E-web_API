package com.example.ecommere.repository;

import com.example.ecommere.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
