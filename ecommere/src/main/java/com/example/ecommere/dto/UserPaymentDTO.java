package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserPaymentDTO {

    private Long id;

    @NotNull
    private Long user_id;

    @NotBlank(message = "Payment method is required")
    private String payment_type;
}
