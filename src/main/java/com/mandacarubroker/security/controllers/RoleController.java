package com.mandacarubroker.security.controllers;

import  com.mandacarubroker.security.models.Role;
import  com.mandacarubroker.security.services.RoleService;
import  com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> elements() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);

    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/roleAdd")
    public ResponseEntity<String> save(Role role) {
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Stock created successfully");

    }

    @DeleteMapping(value = "/role/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.ok("Stock created successfully");
    }

    @RequestMapping("/role/assign/{userId}/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable Integer userId,
                             @PathVariable Integer roleId) {
        roleService.assignUserRole(userId, roleId);
        return ResponseEntity.ok("Role assignment completed successfully");
       // return "redirect:/security/user/Edit/" + userId;
    }

    @RequestMapping("/role/unassign/{userId}/{roleId}")
    public ResponseEntity<String> unassignRole(@PathVariable Integer userId,
                               @PathVariable Integer roleId) {
        roleService.unassignUserRole(userId, roleId);

        return ResponseEntity.ok("Role removal completed successfully");
        //return "redirect:/security/user/Edit/" + userId;
    }
}
