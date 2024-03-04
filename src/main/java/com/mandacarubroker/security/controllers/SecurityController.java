package com.mandacarubroker.security.controllers;



import com.mandacarubroker.elements.dtos.RequestUserDTO;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class SecurityController {
    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyService stockService;

//    @RequestMapping("/login")
//    public String loginPage() {
//        return "security/login";
//    }



    @GetMapping("/register")
    public ResponseEntity<String> addNew(RequestUserDTO data) {
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
                            "users", companyService.findAll().size()
                    ));

    }

    @GetMapping("/accessDenied")
    public ResponseEntity<String>   accessDenied() {

        return ResponseEntity.ok("Access Denied");
    }
}
