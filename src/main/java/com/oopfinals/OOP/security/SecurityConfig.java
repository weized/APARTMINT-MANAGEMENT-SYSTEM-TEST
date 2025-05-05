package com.oopfinals.OOP.security;


import java.io.IOException;
import com.oopfinals.OOP.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/", "/index", "/signup", "/process_register", "/login", "/process_login",
                                "/images/**", "/css/**", "/js/**", "/webjars/**",
                                "/tenant-announcements", "/tenant-index", "/tenant-leave", "/tenant-payments", "/tenant-reports",
                                "/tenant-sidebar", "/tenant-sidebar-button", "/landlord-announcements", "/landlord-dashboard",
                                "/landlord-apartment-info", "/landlord-manage-payments", "/landlord-revenue", "/landlord-sidebar",
                                "/landlord-sidebar-button", "/landlord-view-complaints, ", "/tenant/reports", "/tenant/submit-report, ", "/reports", "/submit-reports"
                        ).permitAll()
                        .requestMatchers("/landlord/**").hasRole("LANDLORD")
                        .requestMatchers("/tenant/**").hasRole("TENANT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            // Check user role
                            authentication.getAuthorities().forEach(authority -> {
                                try {
                                    if (authority.getAuthority().equals("ROLE_LANDLORD")) {
                                        response.sendRedirect("/landlord-dashboard");
                                    } else if (authority.getAuthority().equals("ROLE_TENANT")) {
                                        response.sendRedirect("/tenant-index");
                                    } else {
                                        response.sendRedirect("/default"); // fallback
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }


}



