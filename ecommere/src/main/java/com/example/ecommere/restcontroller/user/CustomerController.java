package com.example.ecommere.restcontroller.user;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.*;
import com.example.ecommere.dto.*;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Product;
import com.example.ecommere.model.User;
import com.example.ecommere.model.UserAddress;
import com.example.ecommere.model.UserPayment;
import com.example.ecommere.service.ProductService;
import com.example.ecommere.service.UserAddressService;
import com.example.ecommere.service.UserPaymentService;
import com.example.ecommere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    /** Service **/
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    UserPaymentService userPaymentService;

    /** Converter **/
    @Autowired
    ProductConverter productConverter;

    @Autowired
    UserAccountConverter userAccountConverter;

    @Autowired
    UserAddressConverter userAddressConverter;

    @Autowired
    AddressListConverter addressListConverter;

    @Autowired
    UserPaymentConverter userPaymentConverter;

    @Autowired
    PaymentListConverter paymentListConverter;

    @GetMapping("/{id}/account")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> viewAccountDetail(@PathVariable(value = "id") Long userId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        User user = userService.get(userId);
        UserAccountDTO userAccountDTO = userAccountConverter.convertToDTO(user);
        if (userAccountDTO == null) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }else if (user.getIsDeleted()) {
            response.setErrorCode(ErrorCode.USER_DELETED_EXCEPTION);
        }else {
            response.setData(userAccountDTO);
            response.setSuccessCode(SuccessCode.USER_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> viewProductDetail(@PathVariable(value = "id") Long productId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Product product = productService.get(productId);
        ProductDTO productDTO = productConverter.convertToDTO(product);
        if (productDTO == null) {
            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        }else {
            response.setData(productDTO);
            response.setSuccessCode(SuccessCode.PRODUCT_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> getListProductsForCustomer(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "10") int limit)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productService.getAll(page, limit)) {
            ProductDTO productDTO = productConverter.convertToDTO(product);
            productDTOList.add(productDTO);
        }
        response.setData(productDTOList);
        response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> getListProductsByCategory(@RequestParam(value = "cate") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (categoryId == null){
            response.setErrorCode(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }else {
            for(Product product : productService.getAllByCategory(categoryId)) {
                ProductDTO productDTO = productConverter.convertToDTO(product);
                productDTOList.add(productDTO);
            }
            response.setData(productDTOList);
            response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/account/address")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> addAddress(@RequestBody UserAddressDTO userAddressDTO,
                                                     @PathVariable(value = "id") Long userId) throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        User user = userService.get(userId);
        if (user == null) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }else {
            UserAddress newUserAddress = userAddressConverter.convertToEntity(userAddressDTO);
            List<UserAddress> userAddressList = userAddressService.addAddress(newUserAddress, userId);
            user.setUserAddressList(userAddressList);
            AddressListDTO addressListDTO = addressListConverter.convertToDTO(user);
            response.setData(addressListDTO);
            response.setSuccessCode(SuccessCode.ADDRESS_ADDED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/account/payment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> addPayment(@RequestBody UserPaymentDTO userPaymentDTO,
                                                  @PathVariable(value = "id") Long userId) throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        User user = userService.get(userId);
        if (user == null) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        }else {
            UserPayment newUserPayment = userPaymentConverter.convertToEntity(userPaymentDTO);
            List<UserPayment> userPaymentList = userPaymentService.addPayment(newUserPayment, userId);
            user.setUserPaymentList(userPaymentList);
            PaymentListDTO paymentListDTO = paymentListConverter.convertToDTO(user);
            response.setData(paymentListDTO);
            response.setSuccessCode(SuccessCode.ADDRESS_ADDED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }
}
