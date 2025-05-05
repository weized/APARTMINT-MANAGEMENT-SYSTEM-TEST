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
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = repo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Loaded user: " + user.getUsername()); // Log here
        return new CustomUserDetails(user);
    }
}
