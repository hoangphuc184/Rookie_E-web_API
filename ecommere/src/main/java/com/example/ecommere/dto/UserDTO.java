package com.example.ecommere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long Id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;


}
