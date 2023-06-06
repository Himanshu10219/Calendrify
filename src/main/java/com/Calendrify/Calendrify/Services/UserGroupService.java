package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.*;
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

    public ResponseEntity<ResponseHandler> getGroupById(String groupId, String userID) {
        try {
            List<Usergroup> list = userGroupRepo.findAll();
            if (!list.isEmpty()) {
                if (userID != null) {
                    List<Usergroup> groupList=new ArrayList<>();
                    List<Usergroup> usergroupList = list.stream()
                            .filter(item -> item.getCreateBy().getId() ==Integer.parseInt(userID))
                            .toList();
                    List<Usergroup> otherList = list.stream()
                            .filter(item -> item.getCreateBy().getId() !=Integer.parseInt(userID))
                            .toList();
                    for (Usergroup usergroup:otherList){
                        System.out.println("userGroupMappingService =="+usergroup.getId().toString());
                        List<Usergroupmapping> usergroupmappingList= userGroupMappingService.getAllUserGroupMapping(null,usergroup.getId().toString(),null);
                        List<Usergroupmapping> filteredList = usergroupmappingList.stream()
                                .filter(mapping -> mapping.getUserID().getId() == Integer.parseInt(userID))
                                .toList();
                        for (Usergroupmapping usergroupmapping:filteredList){
                            System.out.println("usergroupmapping =="+usergroupmapping.getId().toString());
                        }
                        if(filteredList.size()>0){
                            groupList.add(usergroup);
                        }
                    }
                    groupList.addAll(usergroupList);
                    list=groupList;
                }
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
                User user = userRepo.findById(userId).get();
                usergroup.setCreateBy(user);
                Usergroup savedUsergroup = userGroupRepo.save(usergroup);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Group Created!", true, savedUsergroup);
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
