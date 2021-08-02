package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.User;

import com.example.ecommere.model.UserPayment;
import com.example.ecommere.repository.UserPaymentRepository;
import com.example.ecommere.repository.UserRepository;
import com.example.ecommere.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserPayment> addPayment(UserPayment newUserPayment, Long userId) throws DataNotFoundException {
//        try {
//
//        }catch (Exception ex) {
//            throw new DataNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
//        }
        Optional<User> optUser = userRepository.findById(userId);
        User user = optUser.get();
        List<UserPayment> userPaymentList = user.getUserPaymentList();
        newUserPayment.setUser(user);
        String payment = newUserPayment.getPaymentType();
        newUserPayment.setPaymentType(payment.toUpperCase(Locale.ROOT));
        userPaymentRepository.save(newUserPayment);
        userPaymentList.add(newUserPayment);
        return userPaymentList;
    }
}
