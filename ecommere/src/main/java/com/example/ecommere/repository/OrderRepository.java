package com.example.ecommere.repository;

import com.example.ecommere.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
