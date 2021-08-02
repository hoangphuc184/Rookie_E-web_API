package com.example.ecommere.service;

import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.User;
import com.example.ecommere.model.UserAddress;

import java.util.List;

public interface UserAddressService {

    public List<UserAddress> addAddress(UserAddress newUserAddress, Long userId) throws DataNotFoundException;

}
