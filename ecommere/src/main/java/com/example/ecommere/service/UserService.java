package com.example.ecommere.service;

import com.example.ecommere.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();

    public User get(Long userId);

    public User create(User user);

    public void delete(Long userId);

    public User update(User newUser, Long userId);
}
