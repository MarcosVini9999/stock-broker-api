package com.mandacarubroker.security.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestLoginDTO(

        @NotNull(message = "Username cannot be null")
        String username,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&!]).{8,20}$", message = "Symbol must be 4 letters followed by 1 number")
        String password



) {
    }

