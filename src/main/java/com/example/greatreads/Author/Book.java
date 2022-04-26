package com.example.greatreads.Author;

import lombok.Data;

@Data
public class Book {
    private String title;
    private Author author;
    private String type;
    private String description;
}
