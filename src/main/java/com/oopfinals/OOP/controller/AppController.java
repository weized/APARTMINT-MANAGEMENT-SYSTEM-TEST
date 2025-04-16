package com.oopfinals.OOP.controller;


import com.oopfinals.OOP.model.User;
import com.oopfinals.OOP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String redirectToHomePage() {
        return "redirect:/"; // Redirects to the root URL
    }

    @GetMapping("/signup_form")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @GetMapping("/login_form")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(@ModelAttribute User user, Model model) {
        // Check if username or email already exists
        if (repo.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken.");
            return "signup_form"; // Replace with your actual form view name
        }

        if (repo.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email is already registered.");
            return "signup_form";
        }

        // Encode the password and save
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "register_success";
    }

    @PostMapping("/process_login")
    public String processLogin(@ModelAttribute User user, Model model) {
        // Find the user by username
        User existingUser  = repo.findByUsername(user.getUsername());

        // Check if the user exists and if the password matches
        if (existingUser  != null && passwordEncoder.matches(user.getPassword(), existingUser .getPassword())) {
            // Successful login
            return "test"; // Redirect to home page or dashboard
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password.");
            return "login_form"; // Return to login form with error
        }
    }


}
