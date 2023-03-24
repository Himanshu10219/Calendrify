package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.BodyResponse.DateBetweenBody;
import com.Calendrify.Calendrify.Models.BodyResponse.EventCategoryBody;
import com.Calendrify.Calendrify.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;
    @GetMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents(){
        return eventService.getAllEvents();
    }
    @GetMapping("/getEventById/{eventID}")
    public ResponseEntity<?> getEventById(@PathVariable String eventID){
        return eventService.getEventById(Integer.parseInt(eventID));
    }
    @GetMapping("/getEventByCategory/{eventCatID}")
    public ResponseEntity<?> getEventByCategory(@PathVariable String eventCatID){
        return eventService.getEventByCategory(Integer.parseInt(eventCatID));
    }
    @GetMapping("/getEventByUserID/{hostID}")
    public ResponseEntity<?> getEventByUserID(@PathVariable String hostID){
        return eventService.getEventByUserID(Integer.parseInt(hostID));
    }
    @PostMapping("/getEventByMode")
    public ResponseEntity<?> getEventByMode(@RequestBody String online){

        return eventService.getEventByMode(Boolean.parseBoolean(online));
    }
    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @PutMapping("/updateEvent")
    public ResponseEntity<?> updateEvent(@RequestBody Event event){
        return eventService.updateEvent(event);
    }
    @PostMapping("/getEventByDate")
    public ResponseEntity<?> getEventByDate(@RequestBody DateBetweenBody date){
        return eventService.getEventByDate(date.getStartDate(), date.getEndDate());
    }


    @DeleteMapping("/deleteEvent/{eventID}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventID){
        return eventService.deleteEvent(Integer.parseInt(eventID));
    }

}
