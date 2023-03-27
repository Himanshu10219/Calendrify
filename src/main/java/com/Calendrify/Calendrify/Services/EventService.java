package com.Calendrify.Calendrify.Services;

<<<<<<< HEAD
=======
import com.Calendrify.Calendrify.Handlers.ResponseHandler;
>>>>>>> main
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class EventService {
    @Autowired
    EventRepo eventRepo;

<<<<<<< HEAD
    public ResponseEntity<?> getAllEvents(){
        List<Event> list;
        list=eventRepo.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
    }
    public ResponseEntity<?> getEventById(int id){
=======
    public ResponseEntity<ResponseHandler> getAllEvents() {
>>>>>>> main
        try {
            List<Event> list;
            list = eventRepo.findAll();
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist", false, null);
            }
        } catch (Exception e) {
<<<<<<< HEAD
            return  new ResponseEntity<>("Event not exist", HttpStatus.OK);
        }
    }
    public ResponseEntity<?> addEvent(Event ev){
        try {
             eventRepo.save(ev);
            return  new ResponseEntity<>("Event Added", HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>("Some thing went wrong\n"+e.getMessage(), HttpStatus.OK);
        }
    }
    public ResponseEntity<?> getEventByMode(boolean onlineType){
        List<Event> list=new ArrayList<>();
        list=eventRepo.getEventByMode(onlineType);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
    }
=======
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }
>>>>>>> main

    public ResponseEntity<ResponseHandler> getEventById(int id) {
        try {
            if(eventRepo.findById(id).isPresent()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, eventRepo.findById(id).get());
            }else{
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist",false,null);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false,null);
        }
<<<<<<< HEAD
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
=======
>>>>>>> main
    }

    public ResponseEntity<ResponseHandler> addEvent(Event ev) {
        try {
            eventRepo.save(ev);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Added successfully", true);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<ResponseHandler> updateEvent(Event ev) {
        try {
            if(eventRepo.findById(ev.getId()).isPresent()) {
                eventRepo.save(ev);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event update successfully", true);
            }else{
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist", false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<ResponseHandler> deleteEvent(int id) {
        try {
            if(eventRepo.findById(id).isPresent()) {
                eventRepo.deleteById(id);
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Deleted successfully", true);
            }else{
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event not exist", false);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<ResponseHandler> getEventByMode(boolean onlineType) {
        try {
            List<Event> list;
            list = eventRepo.getEventByMode(onlineType);
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", false, null);
            }

        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<ResponseHandler> getEventByDate(String startDate, String endDate) {
        try {
            List<Event> list;
            list = eventRepo.getEventByDate(startDate, endDate);
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", false , null);
            }

        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<ResponseHandler> getEventByCategory(int eventCatID) {
        try {
            List<Event> list;
            list = eventRepo.getEventByCategory(eventCatID);
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", false, null);
            }

        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }

    public ResponseEntity<ResponseHandler> getEventByUserID(int userID) {
        try {
            List<Event> list;
            list = eventRepo.getEventByUserId(userID);
            if (!list.isEmpty()) {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", false,null);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
<<<<<<< HEAD
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
=======
>>>>>>> main
    }
}
