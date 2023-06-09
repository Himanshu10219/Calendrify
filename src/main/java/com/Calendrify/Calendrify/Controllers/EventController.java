package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.BodyResponse.DateBetweenBody;
import com.Calendrify.Calendrify.Repository.EventRepo;
import com.Calendrify.Calendrify.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller
@RequestMapping("/api")
public class EventController {

    EventRepo eventRepo;
    @Autowired
    EventService eventService;

    @GetMapping("event/get")
    public ResponseEntity<ResponseHandler> getAllEvents(@RequestParam(required = false) String eventID,
                                                        @RequestParam(required = false) String eventCatID,
                                                        @RequestParam(required = false) String online,
                                                        @RequestParam(required = false) String hostID) {
        return eventService.getAllEvents(eventID,eventCatID,online,hostID);
    }

    @PostMapping("event/add")
    public ResponseEntity<ResponseHandler> addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @PutMapping("event/update/{eventID}")
    public ResponseEntity<ResponseHandler> updateEvent(@PathVariable String eventID,@RequestBody Event event) {
        return eventService.updateEvent(Integer.parseInt(eventID),event);
    }

    @PostMapping("event/getByDate")
    public ResponseEntity<ResponseHandler> getEventByDate(@RequestBody DateBetweenBody date) {
        return eventService.getEventByDate(date.getStartDate(), date.getEndDate());
    }


    @DeleteMapping("event/delete/{eventID}")
    public ResponseEntity<ResponseHandler> deleteEvent(@PathVariable String eventID) {
        return eventService.deleteEvent(Integer.parseInt(eventID));
    }

}
