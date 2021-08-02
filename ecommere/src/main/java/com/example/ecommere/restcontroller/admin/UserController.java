package com.example.ecommere.restcontroller.admin;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.UserConverter;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.dto.UserDTO;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.User;
import com.example.ecommere.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getListUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "10") int limit)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : userService.getAll(page, limit)) {
            UserDTO userDTO = userConverter.convertToDTO(user);
            userDTOList.add(userDTO);
        }
        response.setData(userDTOList);
        response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable(value = "id") Long userId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        User user = userService.get(userId);
        UserDTO userDTO =  userConverter.convertToDTO(user);
        if (userDTO == null) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }else if (user.getIsDeleted()) {
            response.setData(userDTO);
            response.setErrorCode(ErrorCode.USER_DELETED_EXCEPTION);
        }else {
            response.setData(userDTO);
            response.setSuccessCode(SuccessCode.USER_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable(value = "id") Long userId)
            throws DeleteDataFailException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        User user = userService.delete(userId);
        UserDTO userDTO = userConverter.convertToDTO(user);
        if (userDTO == null) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }else if (!user.getIsDeleted()) {
            response.setData(userDTO);
            response.setErrorCode(ErrorCode.USER_DELETED_FAIL_EXCEPTION);
        }else {
            response.setData(userDTO);
            response.setSuccessCode(SuccessCode.USER_DELETED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }
}
