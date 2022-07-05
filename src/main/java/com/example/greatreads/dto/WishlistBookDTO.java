package com.example.greatreads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishlistBookDTO {
    private String title;
    private String author;
    private String type;
    private String description;

}
