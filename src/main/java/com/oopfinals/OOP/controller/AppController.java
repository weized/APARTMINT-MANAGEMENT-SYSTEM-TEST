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

    @GetMapping("/")
    public String redirectToHomePage() {
        return "redirect:/login"; // Redirects to the root URL
    }

    @GetMapping("/signup")
    public String showSignpp(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("user", new User());
        return "login";

    }

    @PostMapping("/process_register")
    public String processRegistration(@ModelAttribute User user, Model model) {
        // Check if username or email already exists
        if (repo.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken.");
            return "signup"; // Replace with your actual form view name
        }

        if (repo.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email is already registered.");
            return "signup";
        }

        user.setAccount_role("tenant");
        // Encode the password and save
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "login";
    }

    @PostMapping("/process_login")
    public String processLogin(@ModelAttribute User user, Model model) {
        // Find the user by username
        User existingUser = repo.findByUsername(user.getUsername());

        // Check if the user exists and if the password matches
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Successful login
            if("tenant".equalsIgnoreCase(existingUser.getAccount_role())){
                return "/tenant/tenant-index"; // Redirect to home page or dashboard
            } else {
                return "/landlord/apartment";
            }
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password.");
            return "login"; // Return to login form with error
        }
    }


}
