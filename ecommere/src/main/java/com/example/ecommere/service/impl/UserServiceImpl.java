package com.example.ecommere.service.impl;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.model.Product;
import com.example.ecommere.model.User;
import com.example.ecommere.model.UserAddress;
import com.example.ecommere.repository.UserRepository;
import com.example.ecommere.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(int page, int limit) throws DataNotFoundException {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<User> userPage = userRepository.findAll(pageable);
            List<User> users = userPage.getContent();
            log.info("Got User list");
            return users;
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.LIST_USER_NOT_FOUND_EXCEPTION);
        }
    }

    public User get(Long userId) throws DataNotFoundException {
        try {
            Optional<User> optUser = userRepository.findById(userId);
            log.info("Found User: " + optUser.get().getId());
            return optUser.get();
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }
    }

    public User create(User user) throws CreateDataFailException {
        try {
            log.info("Saved User: " + user.getId());
            return userRepository.save(user);
        }catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.USER_CREATED_FAIL_EXCEPTION);
        }
    }

    public User delete(Long userId) throws DeleteDataFailException {
        try {
            Optional<User> optUser = userRepository.findById(userId);
            User user = optUser.get();
            user.setIsDeleted(true);
            log.info("Deleted User: " + user.getId());
            return userRepository.save(user);
        }catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.USER_DELETED_FAIL_EXCEPTION);
        }
    }

    public User update(User newUser, Long userId) throws DataNotFoundException {
        try {
            newUser.setId(userId);
            return userRepository.save(newUser);
        }catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }
    }


}
