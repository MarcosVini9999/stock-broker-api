package com.mandacarubroker.security.domain.dtos;

import com.mandacarubroker.security.domain.entities.User;

public record ResponseUserDTO(Integer id, String firstname, String lastname, String username) {

    public ResponseUserDTO(User user){
        this(user.getId(), user.getFirstname(),user.getLastname(), user.getUsername());
    }
}
