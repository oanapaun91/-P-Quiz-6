package com.example.greatreads;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import javax.validation.Valid;


@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @RequestBody User user) {
//        de verificat daca userul are cont si de tinut o colectie cu userii logati
        return "Bine ai revenit, " + user.getUserName() + "!";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @RequestBody User user) {
//        de adaugat userul intr-o colectie si de verificat
        return "Te-am inregistrat, " + user.getUserName() + "!";
    }
}
