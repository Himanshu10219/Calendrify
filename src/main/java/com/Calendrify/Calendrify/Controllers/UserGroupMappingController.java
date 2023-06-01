package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Usergroup;
import com.Calendrify.Calendrify.Services.UserGroupMappingService;
import com.Calendrify.Calendrify.Services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserGroupMappingController {
    @Autowired
    UserGroupMappingService userGroupMappingService;

    @GetMapping("usergroupmapping/get")
    public ResponseEntity<ResponseHandler> getGroupMapping(@RequestParam(required = false) String mapID,
                                                           @RequestParam(required = false) String groupID,
                                                           @RequestParam(required = false) String userID
    ) {
        return userGroupMappingService.getAllUserGroupMapping(mapID, groupID, userID);
    }

//    @PostMapping("/group/{userId}")
//    public ResponseEntity<ResponseHandler> createGroup(@PathVariable String userId, @RequestBody Usergroup usergroup) {
//        return userGroupService.createGroup(Integer.parseInt(userId), usergroup);
//    }
}
