package com.example.ecommere.service;

import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Discount;

import java.util.List;

public interface DiscountService {

    public List<Discount> getAll() throws DataNotFoundException;

    public Discount get(Long discountId) throws DataNotFoundException;

    public Discount create(Discount discount) throws CreateDataFailException;

    public Discount delete(Long discountId) throws DeleteDataFailException;

    public Discount update(Discount discount, Long discountId) throws DataNotFoundException;
}
