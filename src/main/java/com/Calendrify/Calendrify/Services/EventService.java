package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepo eventRepo;

    public ResponseEntity<?> getAllEvents() {
        try {
            List<Event> list;
            list = eventRepo.findAll();
            if (!list.isEmpty()) {
                return ResponseHandler.GenerateResponse("Success", true,(Object) list);
            } else {
                return ResponseHandler.GenerateResponse("Event not exist", false, null);
            }
        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<?> getEventById(int id) {
        try {
            if(eventRepo.findById(id).isPresent()) {
                return ResponseHandler.GenerateResponse("Success", true, eventRepo.findById(id).get());
            }else{
                return ResponseHandler.GenerateResponse("Event not exist",false,null);
            }
        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false,null);
        }
    }

    public ResponseEntity<?> addEvent(Event ev) {
        try {
            if(eventRepo.findById(ev.getId()).isEmpty()) {
                eventRepo.save(ev);
                return ResponseHandler.GenerateResponse("Event Added successfully", true);
            }else{
                return ResponseHandler.GenerateResponse("Event already exist", false);
            }

        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<?> updateEvent(Event ev) {
        try {
            if(eventRepo.findById(ev.getId()).isPresent()) {
                eventRepo.save(ev);
                return ResponseHandler.GenerateResponse("Event update successfully", true);
            }else{
                return ResponseHandler.GenerateResponse("Event not exist", false);
            }
        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<?> deleteEvent(int id) {
        try {
            if(eventRepo.findById(id).isPresent()) {
                eventRepo.deleteById(id);
                return ResponseHandler.GenerateResponse("Event Deleted successfully", true);
            }else{
                return ResponseHandler.GenerateResponse("Event not exist", false);
            }
        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<?> getEventByMode(boolean onlineType) {
        try {
            List<Event> list;
            list = eventRepo.getEventByMode(onlineType);
            if (!list.isEmpty()) {
                return ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return ResponseHandler.GenerateResponse("Success", false, null);
            }

        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<?> getEventByDate(String startDate, String endDate) {
        try {
            List<Event> list;
            list = eventRepo.getEventByDate(startDate, endDate);
            if (!list.isEmpty()) {
                return ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return ResponseHandler.GenerateResponse("Success", false , null);
            }

        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<?> getEventByCategory(int eventCatID) {
        try {
            List<Event> list;
            list = eventRepo.getEventByCategory(eventCatID);
            if (!list.isEmpty()) {
                return ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return ResponseHandler.GenerateResponse("Success", false, null);
            }

        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<?> getEventByUserID(int userID) {
        try {
            List<Event> list;
            list = eventRepo.getEventByUserId(userID);
            if (!list.isEmpty()) {
                return ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return ResponseHandler.GenerateResponse("Success", false,null);
            }
        } catch (Exception e) {
            return ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }
}
