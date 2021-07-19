package com.example.ecommere.service.impl;

import com.example.ecommere.model.User;
import com.example.ecommere.repository.UserRepository;
import com.example.ecommere.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        log.info(String.valueOf(optUser));
        return optUser.orElse(null);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        optUser.map(user -> {
            user.setIsDeleted(true);
            return userRepository.save(user);
        });
        log.info("User deleted " + optUser.get().getId());
    }

    public User update(User newUser, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setModifiedAt(newUser.getModifiedAt());
                    return userRepository.save(user);
                }).orElseGet(() -> {
                    newUser.setId(userId);
                    return userRepository.save(newUser);
                });
    }
}
