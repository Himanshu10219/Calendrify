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

    @PostMapping("userGroupMapping/add")
    public ResponseEntity<ResponseHandler> saveUserToGroup(@RequestBody List<Usergroupmapping> usergroupmappingList) {
        return userGroupMappingService.saveUsersToGroup(usergroupmappingList);
    }

    @PostMapping("userGroupMapping/delete")
    public ResponseEntity<ResponseHandler> deleteUserToGroup(@RequestBody List<Usergroupmapping> usergroupmappingList) {
        return userGroupMappingService.deleteUsersToGroup(usergroupmappingList);
    }

    @DeleteMapping("userGroupMapping/delete/{mapID}")
    public ResponseEntity<ResponseHandler> deleteMap(@PathVariable String mapID) {
        return userGroupMappingService.deleteMap(Integer.parseInt(mapID));
    }
}
