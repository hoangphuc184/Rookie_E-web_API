package com.example.ecommere.dto;

import com.example.ecommere.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {

    private Long id;

    @NotNull
    private Long user_id;

    private List<CartItemDTO> cartItemList;

    private Double total;
}
