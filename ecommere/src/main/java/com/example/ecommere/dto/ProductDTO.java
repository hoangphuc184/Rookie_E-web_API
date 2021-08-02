package com.example.ecommere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String desc;

    @NotNull
    private Long quantity;

    @NotNull
    private Double price;

    @NotNull
    private Long category_id;

    private Long discount_id;

    @NotNull
    @NotEmpty
    private List<ImageDTO> images;
}
