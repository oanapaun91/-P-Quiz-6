package com.example.greatreads.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class  AddBookDTO {
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String type;
    @NotBlank
    @NotNull
    private String description;
}
