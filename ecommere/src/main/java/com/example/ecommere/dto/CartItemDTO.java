package com.example.ecommere.dto;

import com.example.ecommere.model.Carts;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CartItemDTO {

    private Long id;

    @NotNull
    private Long cart_id;

    @NotNull
    private Long product_id;

    @NotNull
    private Long quantity;
}
