package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Eventcategory;
import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Models.Usergroup;
import com.Calendrify.Calendrify.Services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserGroupController {

    @Autowired
    UserGroupService userGroupService;

    @GetMapping("userGroup/get")
    public ResponseEntity<ResponseHandler> getGroupById(@RequestParam(required = false) String groupId,
                                                        @RequestParam(required = false) String userID){
        return userGroupService.getGroupById(groupId,userID);
    }

    @GetMapping("userGroup/getGroupWithUsers")
    public ResponseEntity<ResponseHandler> getGroupWithUsers(@RequestParam(required = false) String userID){
        return userGroupService.getGroupWithUsers(userID);
    }

    @DeleteMapping("userGroup/delete/{groupId}")
    public ResponseEntity<ResponseHandler> deleteEventCategory(@PathVariable String groupId){
        return userGroupService.deleteEventCategory(Integer.parseInt(groupId));
    }

    @PutMapping("userGroup/update/{groupId}")
    public ResponseEntity<ResponseHandler> updateEventCategoryById(@PathVariable String groupId, @RequestBody Usergroup user){
        return userGroupService.updateEventCategoryById(Integer.parseInt(groupId),user);
    }

    @PostMapping("userGroup/add/{userId}")
    public ResponseEntity<ResponseHandler> createGroup(@PathVariable String userId, @RequestBody Usergroup usergroup) {
        return userGroupService.createGroup(Integer.parseInt(userId), usergroup);
    }
}
