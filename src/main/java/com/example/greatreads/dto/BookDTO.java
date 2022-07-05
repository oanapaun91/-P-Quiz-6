package com.example.greatreads.dto;

import com.example.greatreads.Model.ApprovedStatus;
import com.example.greatreads.Model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDTO {
    @NotNull(message = "Tile must be not-null")
    @NotBlank(message = "Title must be a non-empty string")
    private String title;
    @NotNull(message = "Type must be not-null")
    @NotBlank(message = "Type must be a non-empty string")
    private String type;
    @NotNull(message = "Description must be not-null")
    @NotBlank(message = "Description must be a non-empty string")
    private String description;
    @NotNull(message = "Author must be not-null")
    @NotBlank(message = "Author must be a non-empty string")
    private String author;
    private ApprovedStatus approvedStatus;

    public BookDTO(String title, String type, String description, String author, ApprovedStatus approvedStatus) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.author = author;
        this.approvedStatus = approvedStatus;
    }

    public BookDTO(){}
}
