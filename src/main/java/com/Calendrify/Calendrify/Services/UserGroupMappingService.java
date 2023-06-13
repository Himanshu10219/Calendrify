package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Usergroupmapping;
import com.Calendrify.Calendrify.Repository.UserGroupMappingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class UserGroupMappingService {
    @Autowired
    UserGroupMappingRepo userGroupMappingRepo;

    public List<Usergroupmapping> getAllUserGroupMapping(String mapID, String groupID, String userID) {
        List<Usergroupmapping> list = new ArrayList<>();
        try {
            list = userGroupMappingRepo.findAll();
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
                return list;
            } else {
                return list;
            }
        } catch (Exception e) {
            return list;
        }
    }

    public ResponseEntity<ResponseHandler> deleteMap(int id) {
        try {
            if (userGroupMappingRepo.findById(id).isPresent()) {
                userGroupMappingRepo.deleteById(id);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Map Deleted successfully",
                        true);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Map not exist", false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    @Transactional
    public ResponseEntity<ResponseHandler> saveUsersToGroup(List<Usergroupmapping> usergroupmappingList) {
        int size = usergroupmappingList.size();
        int counter = 0;
        try {
            List<Usergroupmapping> temp = new ArrayList<>();
            for (Usergroupmapping userGroupMap : usergroupmappingList) {
                temp.add(userGroupMap);
                if ((counter + 1) % 1000 == 0 || (counter + 1) == size) {
                    userGroupMappingRepo.saveAll(temp);
                    temp.clear();
                }
                counter++;
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Member added!!", true);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler
                    .GenerateResponse("Member not added!!" + e.getMessage(), true);
        }
    }

    @Transactional
    public ResponseEntity<ResponseHandler> deleteUsersToGroup(List<Usergroupmapping> usergroupmappingList) {
        int size = usergroupmappingList.size();
        int counter = 0;
        try {
            List<Usergroupmapping> temp = new ArrayList<>();
            for (Usergroupmapping userGroupMap : usergroupmappingList) {
                temp.add(userGroupMap);
                if ((counter + 1) % 1000 == 0 || (counter + 1) == size) {
                    userGroupMappingRepo.deleteAll(temp);
                    temp.clear();
                }
                counter++;
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Member added!!", true);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler
                    .GenerateResponse("Member not added!!" + e.getMessage(), true);
        }
    }
}
