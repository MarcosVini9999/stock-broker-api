package com.mandacarubroker.security.domain.dtos;

import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestUserDTO(

           @NotNull(message = "Firstname cannot be null")
           String firstname,

           @NotNull(message = "Lastname cannot be null")
           String lastname,

          @NotNull(message = "Username cannot be null")
           String username,

           @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&!]).{8,20}$", message = "Symbol must be 4 letters followed by 1 number")
          String password,
           @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&!]).{8,20}$", message = "Symbol must be 4 letters followed by 1 number")
          String confirmpassword



) {

    public void isPasswordMatching() {
        if (password != null && !password.equals(confirmpassword)) {
            throw new DataIntegratyViolationException("Passwords do not match");
        }
    }
}
