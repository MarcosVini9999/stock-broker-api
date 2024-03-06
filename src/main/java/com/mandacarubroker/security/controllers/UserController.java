package com.mandacarubroker.security.controllers;

import com.mandacarubroker.security.domain.dtos.ResponseUserDTO;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/security")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<ResponseUserDTO>> getAll() {
            return ResponseEntity.ok(userService.findAll());

        }



@GetMapping("/user/details/{id}")
public ResponseEntity<?>  detailsUserById(@PathVariable int id) {
    return ResponseEntity.ok(userService.findById(id));

}



    @DeleteMapping(value="/users/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("Stock deleted successfully");
    }
}


