package com.oopfinals.OOP.service;

import com.oopfinals.OOP.model.User;
import com.oopfinals.OOP.repository.UserRepository;
import com.oopfinals.OOP.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get the currently authenticated user based on Spring Security context.
     * @return User object if authenticated, otherwise null.
     */
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            return userDetails.getUser();
        }
        return null;
    }

    /**
     * Get user by ID.
     * @param id the user ID.
     * @return Optional containing the user if found.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Get user by username.
     * @param username the username.
     * @return User object or null.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Check if the authenticated user has a given role.
     * @param roleName role to check (e.g., "tenant", "landlord").
     * @return true if the role matches.
     */

}
