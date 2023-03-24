package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId){
        return userService.getUserById(Integer.parseInt(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable String userId){
        System.out.println(userId);
        return userService.deleteUserById(Integer.parseInt(userId));
    }
}
