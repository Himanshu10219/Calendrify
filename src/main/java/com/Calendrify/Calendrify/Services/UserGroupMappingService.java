package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Usergroupmapping;
import com.Calendrify.Calendrify.Repository.UserGroupMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class UserGroupMappingService {
    @Autowired
    UserGroupMappingRepo userGroupMappingRepo;

    public ResponseEntity<ResponseHandler> getAllUserGroupMapping(String mapID, String groupID, String userID) {
        try {
            List<Usergroupmapping> list = userGroupMappingRepo.findAll();
            if (!list.isEmpty()) {
                if (mapID != null) {
                    list = list.stream()
                            .filter(item -> item.getId().equals(Integer.parseInt(mapID)))
                            .collect(Collectors.toList());
                }
                if (groupID != null) {
                    list = list.stream()
                            .filter(item -> item.getGroupID().getId().equals(Integer.parseInt(groupID)))
                            .collect(Collectors.toList());
                }
                if (userID != null) {
                    list = list.stream()
                            .filter(item -> item.getUserID().getId().equals(Integer.parseInt(userID)))
                            .collect(Collectors.toList());
                }
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist", false, null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }
}
