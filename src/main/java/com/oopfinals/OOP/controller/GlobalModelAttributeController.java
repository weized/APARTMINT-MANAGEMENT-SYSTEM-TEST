package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.User;
import com.oopfinals.OOP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice // Applies to all controllers
public class GlobalModelAttributeController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Avoid adding for anonymous (unauthenticated) users
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            String identifier = auth.getName(); // usually username or email
            User user = userRepository.findByUsername(identifier); // or findByUsername if needed

            if (user != null) {
                model.addAttribute("username", user.getUsername());
            }
        }
    }
}
