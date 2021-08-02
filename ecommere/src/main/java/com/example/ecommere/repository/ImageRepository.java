package com.example.ecommere.repository;

import com.example.ecommere.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUrl(@Param("url") String url);
    List<Long> findListById();
}
