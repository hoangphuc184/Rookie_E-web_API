package com.example.ecommere.repository;

import com.example.ecommere.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
