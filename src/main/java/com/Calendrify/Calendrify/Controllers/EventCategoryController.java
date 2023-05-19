package com.Calendrify.Calendrify.Controllers;
import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
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
    @GetMapping("/getAllEventCategory")
    public ResponseEntity<ResponseHandler> getAllEventCategory(@RequestParam(required = false) String eventCatID){
        return eventCategoryService.getAllEventsCategory(eventCatID);
    }

}

