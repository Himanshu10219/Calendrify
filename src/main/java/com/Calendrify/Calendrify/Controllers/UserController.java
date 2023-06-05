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
    @GetMapping("user/get")
    public ResponseEntity<ResponseHandler> getAllUsers(@RequestParam(required = false) String userID,
                                                       @RequestParam(required = false) String email){
        return userService.getAllUsers(userID,email);
    }

    @GetMapping("user/getUserById/{userId}")
    public ResponseEntity<ResponseHandler> getUserById(@PathVariable String userId){
        return userService.getUserById(Integer.parseInt(userId));
    }

    @DeleteMapping("user/delete/{userId}")
    public ResponseEntity<ResponseHandler> deleteUserById(@PathVariable String userId){
        return userService.deleteUserById(Integer.parseInt(userId));
    }

    @PostMapping("user/add")
    public ResponseEntity<ResponseHandler> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "user/update/{userId}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseHandler> updateUserById(@PathVariable String userId, @RequestBody User user){
        System.out.println("=====userId=="+userId);
        return userService.updateUserById(Integer.parseInt(userId),user);
    }
}
