package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Exceptions.ResourceNotFoundException;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.*;
import com.Calendrify.Calendrify.Models.BodyResponse.MailBody;
import com.Calendrify.Calendrify.Models.BodyResponse.NotificationRequest;
import com.Calendrify.Calendrify.Repository.EventCategoryRepo;
import com.Calendrify.Calendrify.Repository.EventRepo;
import com.Calendrify.Calendrify.Repository.UserGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class EventService {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserGroupRepo userGroupRepo;
    @Autowired
    OneSignalService oneSignalService;

    @Autowired
    UserGroupMappingService userGroupMappingService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    private EventCategoryRepo eventCategoryRepo;

    public ResponseEntity<ResponseHandler> getAllEvents(String eventID, String eventCatID, String online, String hostID) {
        try {
            List<Event> list = eventRepo.findAll();
            if (!list.isEmpty()) {
                if (hostID != null) {
                    List<Event> userEventList=new ArrayList<>();
                    List<Event> hostIDList = list.stream()
                            .filter(item -> item.getHostID().getId() ==Integer.parseInt(hostID))
                            .toList();
                    List<Event> otherList = list.stream()
                            .filter(item -> item.getHostID().getId() !=Integer.parseInt(hostID))
                            .toList();
                    for (Event event:otherList){
                        List<Usergroupmapping> usergroupmappingList= userGroupMappingService.getAllUserGroupMapping(null,event.getGroupid().getId().toString(),null);
                        List<Usergroupmapping> filteredList = usergroupmappingList.stream()
                                .filter(mapping -> mapping.getUserID().getId() == Integer.parseInt(hostID))
                                .toList();
                        if(filteredList.size()>0){
                                userEventList.add(event);
                        }
                    }
                    userEventList.addAll(hostIDList);
                    list=userEventList;
                }
                if (eventID != null) {
                    list = list.stream()
                            .filter(item -> item.getId().equals(Integer.parseInt(eventID)))
                            .collect(Collectors.toList());
                }
                if (eventCatID != null) {
                    list = list.stream()
                            .filter(item ->
                                    item.getEventCatID().getId()
                                            .equals(Integer.parseInt(eventCatID)))
                            .collect(Collectors.toList());
                }
                if (online != null) {
                    list = list.stream()
                            .filter(item -> item.getOnline().equals(Boolean.parseBoolean(online)))
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

    public ResponseEntity<ResponseHandler> addEvent(Event ev) {
        try {
            eventRepo.save(ev);
            ResponseEntity<ResponseHandler> response = userGroupService.getGroupWithUsers(String.valueOf(ev.getHostID().getId()));
            Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
            if (responseBody != null) {
                List<GroupWithUsers> groupList = (List<GroupWithUsers>) responseBody.get("data");
                NotificationRequest notificationRequest = new NotificationRequest();
                notificationRequest.setContain(ev.getTitle());
                notificationRequest.setContain(ev.getDescription());
                ArrayList<String> userDeviceTokens = new ArrayList<>();
                 List<String> userEmailsList=new ArrayList<>();
                for (Usergroupmapping usergroupmapping : groupList.get(0).getUserGroupMappingList()) {
                    userDeviceTokens.add(usergroupmapping.getUserID().getDeviceToken());
                    userEmailsList.add(usergroupmapping.getUserID().getEmail());
                }
                MailBody mailBody=new MailBody(userEmailsList,
                        ev.getTitle(),
                        ev.getDescription(),
                        ev.getStartDateTime().format(dateFormatter),
                        ev.getStartDateTime().format(timeFormatter) +" to " +ev.getEndDateTime().format(timeFormatter),
                        ev.getVenueName()
                        );
                notificationRequest.setDeviceTokens(userDeviceTokens);
                sendEventNotification(notificationRequest);
                sendEventMail(mailBody);
            }
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Added successfully", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public void sendEventMail(MailBody mailBody){
        emailSenderService.sendSimpleEmail(mailBody);
    }

    public void sendEventNotification(NotificationRequest notificationRequest){
        oneSignalService.SendNotificationToGroup(notificationRequest);
    }

    public ResponseEntity<ResponseHandler> updateEvent(int eventID, Event event) {
        try {
            Event updateUser = null;
            updateUser = eventRepo.findById(eventID).
                    orElseThrow(() -> new ResourceNotFoundException("Event not Found"));

            updateUser.setTitle(event.getTitle() == null ? updateUser.getTitle() : event.getTitle());
            updateUser.setDescription(event.getDescription() == null ? updateUser.getDescription() : event.getDescription());
            updateUser.setStartDateTime(event.getStartDateTime() == null ? updateUser.getStartDateTime() : event.getStartDateTime());
            updateUser.setEndDateTime(event.getEndDateTime() == null ? updateUser.getEndDateTime() : event.getEndDateTime());
            updateUser.setOnline(event.getOnline() == null ? updateUser.getOnline() : event.getOnline());
            updateUser.setUrl(event.getUrl() == null ? updateUser.getUrl() : event.getUrl());
            updateUser.setVenueName(event.getVenueName() == null ? updateUser.getVenueName() : event.getVenueName());
            updateUser.setAvailability(event.getAvailability() == null ? updateUser.getAvailability() : event.getAvailability());
            Usergroup usergroup=new Usergroup();
            if(userGroupRepo.findById(event.getGroupid().getId()).isPresent()){
                usergroup= userGroupRepo.findById(event.getGroupid().getId()).get();
            }
            updateUser.setGroupid(event.getGroupid() == null ? updateUser.getGroupid() : usergroup);

            Eventcategory eventcategory=new Eventcategory();
            if(eventCategoryRepo.findById(event.getEventCatID().getId()).isPresent()){
                eventcategory= eventCategoryRepo.findById(event.getEventCatID().getId()).get();
            }
            updateUser.setEventCatID(event.getEventCatID() == null ? updateUser.getEventCatID() : eventcategory);
            updateUser.setCreatedAt(event.getCreatedAt() == null ? updateUser.getCreatedAt() : event.getCreatedAt());
            updateUser.setIsDeleted(event.getIsDeleted() == null ? updateUser.getIsDeleted() : event.getIsDeleted());
            updateUser.setLastModify(event.getLastModify() == null ? updateUser.getLastModify() : event.getLastModify());
            eventRepo.save(updateUser);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Updated!!", true, updateUser);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not Updated!!" + e.getMessage(), false);
        }
    }
    public ResponseEntity<ResponseHandler> deleteEvent(int id) {
        try {
            if (eventRepo.findById(id).isPresent()) {
                eventRepo.deleteById(id);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Deleted successfully", true);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist", false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }
    public ResponseEntity<ResponseHandler> getEventByDate(String startDate, String endDate) {
        try {
            List<Event> list;
            list = eventRepo.getEventByDate(startDate, endDate);
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", false, null);
            }

        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }


}