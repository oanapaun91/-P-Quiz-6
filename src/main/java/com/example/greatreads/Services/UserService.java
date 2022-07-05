package com.example.greatreads.Services;

import com.example.greatreads.Model.User;
import com.example.greatreads.Model.UserType;
import com.example.greatreads.Repository.UserRepository;
import com.example.greatreads.Security.UserDetailsImplementation;
import com.example.greatreads.dto.LoginRequestDTO;
import com.example.greatreads.dto.LoginResponseDTO;
import com.example.greatreads.dto.RegisterRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;


    public boolean isAuthor(String email) {
        Optional<User> user = Optional.of(userRepository.findByEmail(email).orElseThrow());

        if (user.get().getUserType().getUserType().equals("author")) {
            return true;
        }
        return false;
    }

    public boolean isAdministrator(String email) {
        Optional<User> user = Optional.of(userRepository.findByEmail(email).orElseThrow());

        if (user.get().getUserType().getUserType().equals("administrator")) {
            return true;
        }
        return false;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email used");
        }

        String userType = String.valueOf(registerRequestDTO.getUserType());

        if (userType == null || !(userType.toUpperCase(Locale.ROOT).equals("READER") || userType.toUpperCase(Locale.ROOT).equals("AUTHOR") || !userType.toUpperCase(Locale.ROOT).equals("ADMINISTRATOR"))) {
            throw new RuntimeException("Invalid role");
        }

        User user = new User(null, registerRequestDTO.getEmail(), encoder.encode(registerRequestDTO.getPassword()), registerRequestDTO.getFirstName(), registerRequestDTO.getLastName(), UserType.valueOf(userType));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtHelper.generateJwtCookie(userDetails);

        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new LoginResponseDTO(userDetails.getEmail(), jwtCookie.getValue(), userDetails.getId(), role));
    }
}
