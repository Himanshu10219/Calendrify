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
    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @GetMapping("/getEventById/{eventID}")
    public ResponseEntity<?> getEventById(@PathVariable String eventID){
        return eventService.getEventById(Integer.parseInt(eventID));
    }
    @PostMapping("/getEventByMode")
    public ResponseEntity<?> getEventByMode(@RequestBody String online){

        return eventService.getEventByMode(Boolean.parseBoolean(online));
    }

    @PostMapping("/getEventByDate")
    public ResponseEntity<?> getEventByDate(@RequestBody DateBetweenBody date){
        return eventService.getEventByDate(date.getStartDate(), date.getEndDate());
    }
    @PostMapping("/getEventByCategory")
    public ResponseEntity<?> getEventByCategory(@RequestBody EventCategoryBody categoryBody){
        System.out.println(categoryBody.getEventCatID());
        return eventService.getEventByCategory( categoryBody.getEventCatID());
    }

}
