package com.mandacarubroker.security.controllers;



import com.mandacarubroker.elements.dtos.RequestLoginDTO;
import com.mandacarubroker.elements.dtos.RequestUserDTO;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.StockService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockService stockService;

    private final AuthenticationManager authenticationManager;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody RequestLoginDTO) {
//
//        return ResponseEntity.ok("Login bem-sucedido");
//    }


    @GetMapping("/login")
    public String loginPage() {
        return "security/login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> processLogin(@RequestBody RequestLoginDTO loginForm) {
        // Lógica para processar o login, verificar as credenciais, etc.
        // Retorna a resposta apropriada, como um token JWT, mensagem de sucesso, etc.
        return ResponseEntity.ok("Login bem-sucedido");
    }



    public SecurityController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
