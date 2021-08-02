package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddressListDTO {

    private Long Id;

    private List<UserAddressDTO> userAddressList;
}
