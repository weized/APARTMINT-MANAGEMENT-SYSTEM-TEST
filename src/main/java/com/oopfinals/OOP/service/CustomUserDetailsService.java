package com.oopfinals.OOP.service;

import com.oopfinals.OOP.model.User;
import com.oopfinals.OOP.repository.UserRepository;
import com.oopfinals.OOP.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String role = user.getAccount_role();
        if (role.startsWith("ROLE_")) {
            role = role.substring(5); // Remove "ROLE_" prefix if it exists
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(role.toUpperCase()) // Now it's safe to use .roles()
                .build();
    }
}

