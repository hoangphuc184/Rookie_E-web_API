package com.example.ecommere.dto;

import com.example.ecommere.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserAddressDTO {

    private Long id;

    @NotNull
    private Long user_id;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;
}
