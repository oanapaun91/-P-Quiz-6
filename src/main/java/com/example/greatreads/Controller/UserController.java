package com.example.greatreads.Controller;

import com.example.greatreads.Services.UserService;
import com.example.greatreads.dto.LoginRequestDTO;
import com.example.greatreads.dto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}
