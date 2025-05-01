package com.oopfinals.OOP.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect-by-role")
    public String redirectByRole(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_LANDLORD"))) {
            return "/landlord/landlordmenupage";  // Change to your actual dashboard URL
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_TENANT"))) {
            return "/tenant/tenant-index";  // Optional: if you support tenants
        }

        return "redirect:/login?error"; // fallback
    }
}
