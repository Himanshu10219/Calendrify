package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<ResponseHandler> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseHandler> getUserById(@PathVariable String userId){
        return userService.getUserById(Integer.parseInt(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseHandler> deleteUserById(@PathVariable String userId){
        return userService.deleteUserById(Integer.parseInt(userId));
    }

    @RequestMapping(value = "/users/save",method = RequestMethod.POST)
    public ResponseEntity<ResponseHandler> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/users/{userId}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseHandler> updateUserById(@PathVariable String userId, @RequestBody User user){
        return userService.updateUserById(Integer.parseInt(userId),user);
    }
}
