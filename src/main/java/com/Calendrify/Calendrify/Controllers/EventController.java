package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.User;
import com.Calendrify.Calendrify.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }
}
