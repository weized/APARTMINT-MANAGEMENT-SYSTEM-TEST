package com.oopfinals.OOP.dto;

import com.oopfinals.OOP.model.User;

public class UserDTO {
    public Long id;
    public String name;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
    }
}
