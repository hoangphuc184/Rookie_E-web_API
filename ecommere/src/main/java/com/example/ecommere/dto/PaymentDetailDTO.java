package com.example.ecommere.dto;

import com.example.ecommere.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PaymentDetailDTO {

    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    private String status;

}
