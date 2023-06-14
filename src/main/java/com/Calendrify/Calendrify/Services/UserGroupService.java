package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Exceptions.ResourceNotFoundException;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.*;
import com.Calendrify.Calendrify.Repository.EventCategoryRepo;
import com.Calendrify.Calendrify.Repository.UserGroupRepo;
import com.Calendrify.Calendrify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private EventCategoryRepo eventCategoryRepo;

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

    public ResponseEntity<ResponseHandler> getGroupWithUsers(String userID) {
        try {
            List<GroupWithUsers> groupWithUsersList=new ArrayList<>();
            ResponseEntity<ResponseHandler> response = getGroupById(null,userID);
            Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
            if (responseBody != null) {
                List<Usergroup> list = (List<Usergroup>) responseBody.get("data");
                if (!list.isEmpty()) {
                    for (Usergroup usergroup : list) {
                        List<Usergroupmapping> usergroupmappingList = userGroupMappingService.getAllUserGroupMapping(null, usergroup.getId().toString(), null);
                        groupWithUsersList.add(new GroupWithUsers(usergroup, usergroupmappingList));
                    }
                }
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, groupWithUsersList);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Something went wrong!", false, null);
        }
    }

    public ResponseEntity<ResponseHandler> updateEventCategoryById(int id, Usergroup eventCategory) {
        try {
            Usergroup updateCategory = null;
            updateCategory = userGroupRepo.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Event Category not Found"));
            updateCategory.setName(eventCategory.getName()==null && eventCategory.getName().isEmpty() ?updateCategory.getName(): eventCategory.getName());
            updateCategory.setDescription(eventCategory.getDescription()==null && eventCategory.getDescription().isEmpty() ?updateCategory.getDescription(): eventCategory.getDescription());
            userGroupRepo.save(updateCategory);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category!!", true, updateCategory);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category not Updated!!"+e.getMessage(), true);
        }
    }

    public ResponseEntity<ResponseHandler> deleteEventCategory(int id) {
        try {
            if (userGroupRepo.findById(id).isPresent()) {
                userGroupRepo.deleteById(id);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category Deleted successfully", true);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category not exist", false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }
}
