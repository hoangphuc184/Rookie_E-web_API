package com.example.ecommere.repository;

import com.example.ecommere.model.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
}
