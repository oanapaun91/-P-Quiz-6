package com.example.greatreads.dto;

import com.example.greatreads.model.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterRequestDTO {
    @Email
    private String email;
    private UserType userType;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "password must have digit + lowercase + uppercase + punctuation + symbol")
    private String password;
    @NotNull(message = "firstName must be not-null")
    @NotBlank(message = "firstName must be a non-empty string")
    private String firstName;
    @NotNull(message = "lastName must be not-null")
    @NotBlank(message = "lastName must be a non-empty string")
    private String lastName;
}