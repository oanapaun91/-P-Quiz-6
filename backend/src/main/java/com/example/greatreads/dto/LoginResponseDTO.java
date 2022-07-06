package com.example.greatreads.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private int id;
    private String email;
    private String role;
    private String token;

    public LoginResponseDTO(String email, String token, int id, String role) {
        this.email = email;
        this.token = token;
        this.id = id;
        this.role = role;
    }
}
