package com.example.ecommere.repository;

import com.example.ecommere.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateNewCategorySuccess() {
        Category fungus = new Category();
        fungus.setCategoryName("Fungus");
        fungus.setCreatedAt(LocalDateTime.now());
        fungus.setModifiedAt(LocalDateTime.now());
        fungus.setDeleted(false);
        assertNotNull(categoryRepository.save(fungus));
    }

//    @Test
//    public void testGetOneCategorySuccess() {
//        Category fungus = new Category(4L, "Bras", LocalDateTime.now(), LocalDateTime.now(), false);
//
//        categoryRepository.save(fungus);
//        Optional<Category> actual = categoryRepository.findById(4L);
//        Category actualReturn = actual.get();
//        assertEquals(fungus.getId(), actualReturn.getId());
//    }
//
//    @Test
//    public void testGetListCategorySuccess() {
////        List<Category> categories = new ArrayList<>();
//        Category fungus = new Category("abc", LocalDateTime.now(), LocalDateTime.now(), false);
//        Category fun = new Category("abcd", LocalDateTime.now(), LocalDateTime.now(), false);
//        Category fuu = new Category("abcde", LocalDateTime.now(), LocalDateTime.now(), false);
////        categories.add(fungus);
////        categories.add(fun);
////        categories.add(fuu);
//
//        categoryRepository.save(fungus);
//        categoryRepository.save(fun);
//        categoryRepository.save(fuu);
//        List<Category> expected = new ArrayList<>();
//        System.out.println(expected);
//        System.out.println(categoryRepository.findAll());
//        assertNotEquals(expected, categoryRepository.findAll());
//    }
//
//    @Test
//    public void testUpdateOneCategorySuccess() {
//        Category category = new Category(11L,"zxc", LocalDateTime.now(), LocalDateTime.now(), false);
//        Category newCategory = new Category("abd", LocalDateTime.now(), LocalDateTime.now(), false);
//        categoryRepository.save(category);
//        categoryRepository.findById(11L).map(category1 -> {
//            category1.setCategoryName(newCategory.getCategoryName());
//            return categoryRepository.save(category1);
//        });
//        Optional<Category> optCate = categoryRepository.findById(11L);
//        Category actual = optCate.get();
//        assertEquals(newCategory.getCategoryName(), actual.getCategoryName());
//        assertNotEquals(category.getCategoryName(), actual.getCategoryName());
//    }

//    @Test
//    public void testDeleteOneCategorySuccess() {
//        Category category = new Category(1L,"bdd", LocalDateTime.now(), LocalDateTime.now(), false);
//        categoryRepository.save(category);
//        categoryRepository.delete(category);
//        List<Category> categories = categoryRepository.findAll();
//        Optional<Category> opt = categoryRepository.findById(1L);
//        Mockito.
//    }
}
