package com.mandacarubroker.security.controllers;


import com.mandacarubroker.security.TokenService;

import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.StockService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;

import com.mandacarubroker.security.domain.dtos.LoginResponseDTO;
import com.mandacarubroker.security.domain.dtos.RequestLoginDTO;
import com.mandacarubroker.security.domain.dtos.RequestUserDTO;
import com.mandacarubroker.security.domain.entities.UserPrincipal;
import com.mandacarubroker.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private final StockService stockService;
    private final CompanyService companyService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public SecurityController(StockService stockService,
                              CompanyService companyService,
                              UserService userService,
                              AuthenticationManager authenticationManager,
                              TokenService tokenService) {
        this.stockService = stockService;
        this.companyService = companyService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid RequestLoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserPrincipal) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
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


    @RequestMapping("/index")
    public ResponseEntity<Map<String, Integer>> homePage() {
        return ResponseEntity.ok()
                .body(Map.of(
                        "stocks", stockService.findAll().size(),
                        "companies", companyService.findAll().size(),
                        "users", userService.findAll().size()
                ));

    }

    @GetMapping("/accessDenied")
    public ResponseEntity<String>   accessDenied() {

        return ResponseEntity.ok("Access Denied");
    }
}
