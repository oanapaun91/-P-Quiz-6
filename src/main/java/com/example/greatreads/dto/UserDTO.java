package com.example.greatreads.dto;

import com.example.greatreads.Model.UserType;
import lombok.Data;

@Data
public class UserDTO {
   private String firstName;
   private String lastName;
   private UserType userType;
}
