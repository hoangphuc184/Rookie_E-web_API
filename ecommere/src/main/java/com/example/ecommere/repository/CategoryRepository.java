package com.example.ecommere.repository;

import com.example.ecommere.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
