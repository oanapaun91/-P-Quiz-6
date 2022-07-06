package com.example.greatreads.security;

import com.example.greatreads.model.User;
import com.example.greatreads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImplementation loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImplementation.build(user);
    }
}
