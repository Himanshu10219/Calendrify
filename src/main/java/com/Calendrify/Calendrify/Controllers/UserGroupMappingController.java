package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Usergroup;
import com.Calendrify.Calendrify.Models.Usergroupmapping;
import com.Calendrify.Calendrify.Services.UserGroupMappingService;
import com.Calendrify.Calendrify.Services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserGroupMappingController {
    @Autowired
    UserGroupMappingService userGroupMappingService;

    @GetMapping("userGroupMapping/get")
    public ResponseEntity<ResponseHandler> getGroupMapping(@RequestParam(required = false) String mapID,
            @RequestParam(required = false) String groupID,
            @RequestParam(required = false) String userID) {
        return userGroupMappingService.getAllUsersFromGroup(mapID, groupID, userID);
    }

    @PostMapping("userGroupMapping/add")
    public ResponseEntity<ResponseHandler> saveUserToGroup(@RequestBody List<Usergroupmapping> usergroupmappingList) {
        return userGroupMappingService.saveUsersToGroup(usergroupmappingList);
    }

    @DeleteMapping("usergroupmapping/delete/{mapID}")
    public ResponseEntity<ResponseHandler> deleteMap(@PathVariable String mapID) {
        return userGroupMappingService.deleteMap(Integer.parseInt(mapID));
    }

    //
    // @GetMapping("usergroupmapping/get")
    // public ResponseEntity<ResponseHandler> getGroupMapping(@RequestParam(required
    // = false) String mapID,
    // @RequestParam(required = false) String groupID,
    // @RequestParam(required = false) String userID
    // ) {
    // return userGroupMappingService.getAllUserGroupMapping(mapID, groupID,
    // userID);
    // }

    // @PostMapping("/group/{userId}")
    // public ResponseEntity<ResponseHandler> createGroup(@PathVariable String
    // userId, @RequestBody Usergroup usergroup) {
    // return userGroupService.createGroup(Integer.parseInt(userId), usergroup);
    // }
}
