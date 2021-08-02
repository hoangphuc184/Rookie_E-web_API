package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserPaymentDTO {

    private Long id;

    @NotBlank(message = "Payment method is required")
    private String paymentType;
}
