package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Exceptions.ResourceNotFoundException;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Repository.EventRepo;
import com.Calendrify.Calendrify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class UserService {
    @Autowired
    UserRepo userRepo;
    EventService eventService;
    @Autowired
    private EventRepo eventRepo;


    public ResponseEntity<ResponseHandler> getAllUsers(){
        try {
            List<User> allUsers = new ArrayList<>();
            allUsers = userRepo.findAll();
            if (allUsers != null) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success!",true,allUsers);

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!",false,null);
    }

    public ResponseEntity<ResponseHandler> getUserById(int id){
        try {
            User user = userRepo.findById(id).get();
            if (user != null) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success!",true,
                        user);

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!",false,null);

    }

    public ResponseEntity<ResponseHandler> deleteUserById(int id){
        try {
            User isExistingUser=userRepo.findById(id).get();
            if(isExistingUser!=null) {
                List<Event> eventsHostedByUserId = eventRepo.getEventByUserId(id);
                if (eventsHostedByUserId != null) {
                    for (Event e : eventsHostedByUserId) {
                        eventRepo.deleteById(e.getId());
                    }
                    userRepo.deleteById(id);
                }
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("User Deleted!",true);
            }
            else{
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Use Not Found with " +id,false);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!",false,null);
    }

    public ResponseEntity<ResponseHandler> addUser(User user) {
        try{
            userRepo.save(user);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("User Added!",true);
        }catch (Exception e){
            System.out.println("Save User Error:"+e.getMessage());
        }
        return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!",false);
    }


    public ResponseEntity<ResponseHandler> updateUserById(int id,User user) {
        User updateUser=null;
        try {
                updateUser = userRepo.findById(id).
                        orElseThrow(() -> new ResourceNotFoundException("User not Found"));

                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
                updateUser.setCreatedAt(user.getCreatedAt());
                updateUser.setIsDeleted(user.getIsDeleted());
                updateUser.setFirstName(user.getFirstName());
                updateUser.setLastName(user.getLastName());
                updateUser.setMobile(user.getMobile());
                userRepo.save(updateUser);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("User Updated!!",true,updateUser);



    }
}
