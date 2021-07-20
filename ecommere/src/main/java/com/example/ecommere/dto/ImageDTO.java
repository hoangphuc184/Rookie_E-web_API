package com.example.ecommere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ImageDTO {

    private Long id;

    @NotBlank(message = "url is required")
    private String url;

    @NotBlank(message = "Description is required")
    private String desc;
}
