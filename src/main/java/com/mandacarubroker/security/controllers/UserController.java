package com.mandacarubroker.security.controllers;

import com.mandacarubroker.elements.dtos.RequestUserDTO;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.security.models.Role;
import  com.mandacarubroker.security.models.User;
import  com.mandacarubroker.security.services.RoleService;
import  com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/security")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
            return ResponseEntity.ok(userService.findAll());

        }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<String> editUser(@PathVariable Integer id) {
        User user = userService.findById(id);
        Set<Role> userRoles = roleService.getUserRoles(user);
        List<Role> userNotRoles = roleService.getUserNotRoles(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("userRoles", userRoles);
        response.put("userNotRoles", userNotRoles);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User role update successfully");
    }


//@GetMapping("/user/details/{id}")
//public ResponseEntity<?> detailsUser(@PathVariable Integer id) {
//    User user = userService.findById(id);
//    Set<Role> userRoles = roleService.getUserRoles(user);
//    List<Role> userNotRoles = roleService.getUserNotRoles(user);
//
//    Map<String, Object> response = new HashMap<>();
//    response.put("user", user);
//    response.put("userRoles", userRoles);
//    response.put("userNotRoles", userNotRoles);
//
//    return ResponseEntity.ok(response);
//}
//
//
//



    @DeleteMapping(value="/users/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("Stock deleted successfully");
    }
}


