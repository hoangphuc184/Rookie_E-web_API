package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.model.User;
import com.example.ecommere.model.UserAddress;
import com.example.ecommere.repository.UserAddressRepository;
import com.example.ecommere.repository.UserRepository;
import com.example.ecommere.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    public List<UserAddress> addAddress(UserAddress newUserAddress, Long userId) throws DataNotFoundException {
        try {
            Optional<User> optUser = userRepository.findById(userId);
            User user = optUser.get();
            List<UserAddress> userAddressList = user.getUserAddressList();
            newUserAddress.setUser(user);
            userAddressRepository.save(newUserAddress);
            userAddressList.add(newUserAddress);
            return userAddressList;
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }
    }

}
