package com.mandacarubroker.security.controller;


import com.mandacarubroker.security.domain.dtos.ResponseUserDTO;
import com.mandacarubroker.security.domain.user.User;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;



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
public ResponseEntity<User>  detailsUserById(@PathVariable int id) {
    return ResponseEntity.ok(userService.findById(id));

}



    @DeleteMapping(value="/users/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("Stock deleted successfully");
    }
}


