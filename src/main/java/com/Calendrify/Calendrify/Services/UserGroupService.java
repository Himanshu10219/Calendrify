package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.*;
import com.Calendrify.Calendrify.Repository.UserGroupMappingRepo;
import com.Calendrify.Calendrify.Repository.UserGroupRepo;
import com.Calendrify.Calendrify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class UserGroupService {

    @Autowired
    UserGroupRepo userGroupRepo;

    @Autowired
    UserGroupMappingService userGroupMappingService;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<ResponseHandler> getGroupById(String groupId) {
        try {
            List<Usergroup> list = userGroupRepo.findAll();
            if (!list.isEmpty()) {
                if (groupId != null) {
                    list = list.stream()
                            .filter(item -> item.getId().equals(Integer.parseInt(groupId)))
                            .collect(Collectors.toList());
                }
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!", false, null);
        }
    }

    public ResponseEntity<ResponseHandler> createGroup(int userId, Usergroup usergroup) {
        try {
            if (userRepo.findById(userId).isPresent()) {
                User isExistingUser = userRepo.findById(userId).get();
                usergroup.setCreateBy(isExistingUser);
                userGroupRepo.save(usergroup);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Group Created!", true, usergroup);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("User not exist with " + userId, false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went Wrong! ", false);
        }
    }

    public ResponseEntity<ResponseHandler> getGroupWithUsers() {
        try {
            List<GroupWithUsers> groupWithUsersList=new ArrayList<>();
            List<Usergroup> list = userGroupRepo.findAll();
            if (!list.isEmpty()) {
                for(Usergroup usergroup:list){
                    List<Usergroupmapping> usergroupmappingList= userGroupMappingService.getAllUserGroupMapping(null,usergroup.getId().toString(),null);
                    groupWithUsersList.add(new GroupWithUsers(usergroup,usergroupmappingList));
                }
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, groupWithUsersList);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!", false, null);
        }
    }
}
