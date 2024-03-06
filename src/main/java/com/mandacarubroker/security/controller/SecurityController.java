package com.mandacarubroker.security.controller;

import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.security.domain.dtos.RequestUserDTO;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RequestUserDTO> login(@RequestBody RequestUserDTO data){
        return ResponseEntity.ok(data);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNew(@RequestBody RequestUserDTO data) {
        try {
            data.isPasswordMatching();
            userService.validateAndCreateUser(data);
            return ResponseEntity.status(HttpStatus.CREATED).body("Your new user registration was successful!");
        } catch (DataIntegratyViolationException ex) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/accessDenied")
    public ResponseEntity<String> accessDenied() {

        return ResponseEntity.ok("Access Denied");
    }
}
