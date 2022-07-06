package com.example.greatreads.dto;

import com.example.greatreads.model.UserType;
import lombok.Data;

@Data
public class UserDTO {
   private String firstName;
   private String lastName;
   private UserType userType;
}
