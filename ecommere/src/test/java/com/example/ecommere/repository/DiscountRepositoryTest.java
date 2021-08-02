package com.example.ecommere.repository;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.Discount;
import com.example.ecommere.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DiscountRepositoryTest {

    @Autowired
    DiscountRepository discountRepository;

    @Test
    public void testGetAllDiscount() {
        List<Discount> discounts = discountRepository.findAll();
        assertNotNull(discounts);
    }

    @Test
    public void testGetDiscountById() throws DataNotFoundException {
        Optional<Discount> optDis = discountRepository.findById(1L);
        Discount discount;
        if (optDis.isPresent()) {
            discount = optDis.get();
        } else throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);

        assertNotNull(discount);
    }

    @Test
    public void testCreateDiscount() {
        Discount discount = new Discount();
        discount.setName("discount 65");
        discount.setDesc("discount 65% of price");
        discount.setPercent(0.65);
        List<Product> products = new ArrayList<>();
        discount.setProducts(products);

        assertNotNull(discountRepository.save(discount));
    }

    @Test
    public void testUpdateDiscount() throws DataNotFoundException {
        Optional<Discount> optDis = discountRepository.findById(3L);
        Discount discount;
        if (optDis.isPresent()) {
            discount = optDis.get();
        } else throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);
        discount.setName("Discount 34");
        discount.setPercent(0.34);

        assertNotNull(discountRepository.save(discount));
    }

    @Test
    public void testDeleteDiscount() throws DataNotFoundException {
        Optional<Discount> optDis = discountRepository.findById(3L);
        Discount discount;
        if (optDis.isPresent()) {
            discount = optDis.get();
        } else throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);
        discount.setIsDeleted(true);
        discount.setActive(false);
        discountRepository.save(discount);

        Optional<Discount> actual = discountRepository.findById(3L);
        assertTrue(actual.get().getIsDeleted());
        assertFalse(actual.get().getActive());
    }
}
