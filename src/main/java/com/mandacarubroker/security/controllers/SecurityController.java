package com.mandacarubroker.security.controllers;



import com.mandacarubroker.security.TokenService;
import com.mandacarubroker.security.dtos.LoginResponseDTO;
import com.mandacarubroker.security.dtos.RequestLoginDTO;
import com.mandacarubroker.security.dtos.RequestUserDTO;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.StockService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.security.models.UserPrincipal;
import com.mandacarubroker.security.repositories.UserRepository;
import com.mandacarubroker.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private StockService stockService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;
    

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


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
    public ResponseEntity<?> homePage() {
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
