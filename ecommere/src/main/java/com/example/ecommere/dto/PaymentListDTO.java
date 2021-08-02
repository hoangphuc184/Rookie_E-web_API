package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymentListDTO {

    private Long Id;

    private List<UserPaymentDTO> userPaymentList;
}
