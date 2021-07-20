package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    @NotNull
    private Long order_id;

    @NotNull
    private Long product_id;

    @NotNull
    private Long quantity;
}
