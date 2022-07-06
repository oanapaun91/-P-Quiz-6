package com.example.greatreads.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    AUTHOR("author"),
    READER("reader"),
    ADMINISTRATOR("administrator");

    private final String userType;

}
