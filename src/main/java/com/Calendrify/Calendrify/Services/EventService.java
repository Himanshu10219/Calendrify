package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Exceptions.ResourceNotFoundException;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.*;
import com.Calendrify.Calendrify.Models.BodyResponse.MailBody;
import com.Calendrify.Calendrify.Models.BodyResponse.NotificationRequest;
import com.Calendrify.Calendrify.Repository.EventCategoryRepo;
import com.Calendrify.Calendrify.Repository.EventRepo;
import com.Calendrify.Calendrify.Repository.UserGroupRepo;
import com.Calendrify.Calendrify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class EventService {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd 'th', MMMM yyyy");

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
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<ResponseHandler> getAllEvents(String eventID, String eventCatID, String online, String hostID) {
        try {
            List<Event> list = eventRepo.findAll();
            if (!list.isEmpty()) {
                if (hostID != null) {
                    List<Event> userEventList = new ArrayList<>();
                    List<Event> hostIDList = list.stream()
                            .filter(item -> item.getHostID().getId() == Integer.parseInt(hostID))
                            .toList();
                    List<Event> otherList = list.stream()
                            .filter(item -> item.getHostID().getId() != Integer.parseInt(hostID))
                            .toList();
                    for (Event event : otherList) {
                        List<Usergroupmapping> usergroupmappingList = userGroupMappingService.getAllUserGroupMapping(null, event.getGroupid().getId().toString(), null);
                        List<Usergroupmapping> filteredList = usergroupmappingList.stream()
                                .filter(mapping -> mapping.getUserID().getId() == Integer.parseInt(hostID))
                                .toList();
                        if (filteredList.size() > 0) {
                            userEventList.add(event);
                        }
                    }
                    userEventList.addAll(hostIDList);
                    list = userEventList;
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
            //sendEventNotification(ev);
            sendEventMail(ev);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Added successfully", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public void sendEventMail(Event ev) {
        if (eventRepo.findById(ev.getId()).isPresent()) {
            ev = eventRepo.findById(ev.getId()).get();
        }
        List<Usergroupmapping> usergroupmappingList = userGroupMappingService.getAllUserGroupMapping(null, String.valueOf(ev.getGroupid().getId()), null);
        if (!usergroupmappingList.isEmpty()) {
            List<String> userEmailsList = new ArrayList<>();
            for (Usergroupmapping usergroupmapping : usergroupmappingList) {
                User user = userRepo.findById(usergroupmapping.getUserID().getId()).get();
                if(user.getEmail()!=null && !user.getEmail().equals("")){
                    userEmailsList.add(user.getEmail());
                    System.out.println("EMAIL====="+user.getEmail());
                }
            }
            String dayOfWeek = ev.getStartDateTime().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            Usergroup usergroup = userGroupRepo.findById(ev.getGroupid().getId()).get();
            int dayOfMonth = ev.getStartDateTime().getDayOfMonth();
            String month = ev.getStartDateTime().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            User user = userRepo.findById(ev.getHostID().getId()).get();
            MailBody mailBody = new MailBody(
                    userEmailsList,
                    ev.getStartDateTime().format(dateTimeFormatter),
                    ev.getTitle(),
                    user.getFirstName() + " " + user.getLastName(),
                    ev.getDescription(),
                    ev.getUrl(),
                    ev.getStartDateTime().format(timeFormatter) + " to " + ev.getEndDateTime().format(timeFormatter),
                    usergroup.getName(),
                    ev.getVenueName()
            );
            emailSenderService.sendSimpleEmail(mailBody);
        }
    }

    public void sendEventNotification(Event ev) {
        if (eventRepo.findById(ev.getId()).isPresent()) {
            ev = eventRepo.findById(ev.getId()).get();
        }
        List<Usergroupmapping> usergroupmappingList = userGroupMappingService.getAllUserGroupMapping(null, String.valueOf(ev.getGroupid().getId()), null);
        if (!usergroupmappingList.isEmpty()) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            NotificationRequest notificationRequest = new NotificationRequest();
            Duration duration = Duration.between(currentDateTime, ev.getStartDateTime());
            int minutesDifferenceInt = (int) duration.toMinutes();;
            notificationRequest.setHeading(ev.getTitle()+"  "+minutesDifferenceInt+"minutes remaining");
            notificationRequest.setContain(ev.getDescription());
            ArrayList<String> userDeviceTokens = new ArrayList<>();
            for (Usergroupmapping usergroupmapping : usergroupmappingList) {
                User user = userRepo.findById(usergroupmapping.getUserID().getId()).get();
                if(user.getDeviceToken()!=null && !user.getDeviceToken().equals("")){
                    userDeviceTokens.add(user.getDeviceToken());
                }

            }
            notificationRequest.setDeviceTokens(userDeviceTokens);
            oneSignalService.SendNotificationToGroup(notificationRequest);
        }
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
            Usergroup usergroup = new Usergroup();
            if (userGroupRepo.findById(event.getGroupid().getId()).isPresent()) {
                usergroup = userGroupRepo.findById(event.getGroupid().getId()).get();
            }
            updateUser.setGroupid(event.getGroupid() == null ? updateUser.getGroupid() : usergroup);

            Eventcategory eventcategory = new Eventcategory();
            if (eventCategoryRepo.findById(event.getEventCatID().getId()).isPresent()) {
                eventcategory = eventCategoryRepo.findById(event.getEventCatID().getId()).get();
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