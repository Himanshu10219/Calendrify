package com.Calendrify.Calendrify.Controllers;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.Eventcategory;
import com.Calendrify.Calendrify.Models.Usergroup;
import com.Calendrify.Calendrify.Services.EventCategoryService;
import com.Calendrify.Calendrify.Services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventCategoryController {

    @Autowired
    EventCategoryService eventCategoryService;
    @GetMapping("eventCategory/get")
    public ResponseEntity<ResponseHandler> getAllEventCategory(@RequestParam(required = false) String eventCatID){
        return eventCategoryService.getAllEventsCategory(eventCatID);
    }

    @PostMapping("eventCategory/add")
    public ResponseEntity<ResponseHandler> addEventCategory(@RequestBody Eventcategory eventcategory) {
        return eventCategoryService.addEventCategory(eventcategory);
    }

}

