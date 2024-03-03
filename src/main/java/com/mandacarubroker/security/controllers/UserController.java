package com.mandacarubroker.security.controllers;

import com.mandacarubroker.elements.dtos.RequestUserDTO;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import  com.mandacarubroker.security.models.User;
import  com.mandacarubroker.security.services.RoleService;
import  com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/security/users")
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/security/users";
    }

    @GetMapping("/security/user/{op}/{id}")
    public String editUser(@PathVariable Integer id, @PathVariable String op, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/user" + op;
    }


    @PostMapping("/users/addNew")
    public RedirectView addNew(RequestUserDTO data, RedirectAttributes redir) {
        try {
            data.isPasswordMatching();
            userService.validateAndCreateUser(data);
            redir.addFlashAttribute("message", "Your new user registration was successful!");
            return new RedirectView("/login", true);
        } catch (DataIntegratyViolationException ex) {
            redir.addFlashAttribute("message", ex.getMessage());
            return new RedirectView("/register", true);
        }


    }






    @RequestMapping(value="security/users/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/security/users";
    }
}
