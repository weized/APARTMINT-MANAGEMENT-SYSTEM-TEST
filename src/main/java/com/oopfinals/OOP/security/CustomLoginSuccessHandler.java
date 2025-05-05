package com.oopfinals.OOP.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("LANDLORD")) {
                response.sendRedirect("landlord/landlord-dashboard");
                return;
            } else if (authority.getAuthority().equals("TENANT")) {
                response.sendRedirect("tenant/tenant-index");
                return;
            }
        }

        // Default if no role matches
        response.sendRedirect("/login?error");
    }
}
