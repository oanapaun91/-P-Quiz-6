package com.example.greatreads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class LoginRequestDTO {
    @Email
    private String email;
    private String password;
}