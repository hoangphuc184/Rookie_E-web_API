package com.example.ecommere.service;

import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.UserPayment;

import java.util.List;

public interface UserPaymentService {

    public List<UserPayment> addPayment(UserPayment newUserPayment, Long userId) throws DataNotFoundException;
}
