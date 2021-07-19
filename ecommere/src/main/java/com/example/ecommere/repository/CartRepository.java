package com.example.ecommere.repository;

import com.example.ecommere.model.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartRepository extends JpaRepository<Carts, Long> {
}
