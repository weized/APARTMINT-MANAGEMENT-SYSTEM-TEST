package com.oopfinals.OOP.security;

import java.util.Collection;
import java.util.Collections;

import com.oopfinals.OOP.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getAccount_role().toUpperCase()));
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // <--- Fix this to username if you want username login
    }


    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }



}
