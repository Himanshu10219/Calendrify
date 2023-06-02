package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
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
    public ResponseEntity<ResponseHandler> getGroupById(@RequestParam(required = false) String groupId){
        return userGroupService.getGroupById(groupId);
    }

    @GetMapping("userGroup/getGroupWithUsers")
    public ResponseEntity<ResponseHandler> getGroupWithUsers(){
        return userGroupService.getGroupWithUsers();
    }

    @PostMapping("/group/{userId}")
    public ResponseEntity<ResponseHandler> createGroup(@PathVariable String userId, @RequestBody Usergroup usergroup) {
        return userGroupService.createGroup(Integer.parseInt(userId), usergroup);
    }
}
