package com.example.ecommere.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String categoryName;

    private String image;

}
