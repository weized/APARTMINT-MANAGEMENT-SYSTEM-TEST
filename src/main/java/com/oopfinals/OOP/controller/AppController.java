package com.oopfinals.OOP.controller;


import com.oopfinals.OOP.model.User;
import com.oopfinals.OOP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;

    @GetMapping("/index")
    public String redirectToHomePage() {
        return "redirect:/"; // Redirects to the root URL
    }

    @GetMapping("/signup_form")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        repo.save(user);
        return "register_success";
    }
}
