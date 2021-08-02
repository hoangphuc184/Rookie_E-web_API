package com.example.ecommere.service;

import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.User;
import com.example.ecommere.model.UserAddress;

import java.util.List;

public interface UserService {
    public List<User> getAll(int page, int limit) throws DataNotFoundException;

    public User get(Long userId) throws DataNotFoundException;

    public User create(User user) throws CreateDataFailException;

    public User delete(Long userId) throws DeleteDataFailException;

    public User update(User newUser, Long userId) throws DataNotFoundException;



}
