package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ResponseEntity<?> getAllUsers(){
        try {
            List<User> allUsers = new ArrayList<>();
            allUsers = userRepo.findAll();
            if (allUsers != null) {
                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("No record Found!",HttpStatus.OK);
    }

    public ResponseEntity<?> getUserById(int id){
        try {
            User user = userRepo.findById(id).get();
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Not exist!", HttpStatus.OK);

    }

    public ResponseEntity<?> deleteUserById(int id){
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.OK);
    }

}
