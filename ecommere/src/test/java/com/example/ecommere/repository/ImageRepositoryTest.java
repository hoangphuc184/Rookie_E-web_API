package com.example.ecommere.repository;

import com.example.ecommere.model.Image;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ImageRepositoryTest {

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void testGetImageByUrlSuccess() {
        Image image = new Image();
        image.setUrl("test image");
        imageRepository.save(image);
        assertNotNull(imageRepository.findByUrl("test image"));
    }
}
