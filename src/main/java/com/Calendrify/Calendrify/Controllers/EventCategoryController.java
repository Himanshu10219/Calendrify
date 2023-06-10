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
    public ResponseEntity<ResponseHandler> getAllEventCategory(@RequestParam(required = false) String eventCatID,@RequestParam(required = false) String userID){

        return eventCategoryService.getAllEventsCategory(eventCatID,userID);
    }

    @PostMapping("eventCategory/add")
    public ResponseEntity<ResponseHandler> addEventCategory(@RequestBody Eventcategory eventcategory) {
        return eventCategoryService.addEventCategory(eventcategory);
    }

    @DeleteMapping("eventCategory/delete/{eventCatID}")
    public ResponseEntity<ResponseHandler> deleteEventCategory(@PathVariable String eventCatID) {
        return eventCategoryService.deleteEventCategory(Integer.parseInt(eventCatID));
    }

    @PutMapping("eventCategory/update/{eventCatID}")
    public ResponseEntity<ResponseHandler> updateEventCategory(@PathVariable String eventCatID,@RequestBody Eventcategory eventcategory) {
        return eventCategoryService.updateEventCategory(Integer.parseInt(eventCatID),eventcategory);
    }

}

