package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Discount;
import com.example.ecommere.repository.DiscountRepository;
import com.example.ecommere.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public List<Discount> getAll() throws DataNotFoundException {
        try {
            log.info("Got Discount list");
            return discountRepository.findAll();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_DISC_NOT_FOUND_EXCEPTION);
        }
    }

    public Discount get(Long discountId) throws DataNotFoundException {
        try {
            Optional<Discount> optDiscount = discountRepository.findById(discountId);
            log.info("Found discount: " + optDiscount.get().getName());
            return optDiscount.get();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);
        }
    }

    public Discount create(Discount discount) throws CreateDataFailException {
        try {
            log.info("Save discount: " + discount.getName());
            return discountRepository.save(discount);
        }catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.DISCOUNT_CREATED_FAIL_EXCEPTION);
        }
    }

    public Discount delete(Long discountId) throws DeleteDataFailException {
        try {
            Optional<Discount> optDiscount = discountRepository.findById(discountId);
            Discount discount = optDiscount.get();
            discount.setIsDeleted(true);
            log.info("Deleted discount: " + discount.getName());
            return discountRepository.save(discount);
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.DISCOUNT_DELETED_FAIL_EXCEPTION);
        }
    }

    public Discount update(Discount newDiscount, Long discountId) throws DataNotFoundException {
        try {
            return discountRepository.findById(discountId)
                    .map(discount -> {
                        discount.setName(newDiscount.getName());
                        discount.setDesc(newDiscount.getDesc());
                        discount.setPercent(newDiscount.getPercent());
                        discount.setActive(newDiscount.getActive());
                        discount.setModifiedAt(newDiscount.getModifiedAt());
                        log.info("Updated discount: " + discount.getName());
                        return discountRepository.save(discount);
                    }).orElseGet(() -> {
                        newDiscount.setId(discountId);
                        log.info("Create new discount: " + newDiscount.getName());
                        return discountRepository.save(newDiscount);
                    });
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND_EXCEPTION);
        }
    }
}
