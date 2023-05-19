package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Eventcategory;
import com.Calendrify.Calendrify.Repository.EventCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@SuppressWarnings("unchecked")
public class EventCategoryService {

    @Autowired
    EventCategoryRepo eventCategoryRepo;
    public ResponseEntity<ResponseHandler> getAllEventsCategory( String eventCatID) {
        try {
            List<Eventcategory> list= eventCategoryRepo.findAll();
            System.out.println("getAllEventsCategory="+list);
            if (!list.isEmpty()) {

                if (eventCatID != null) {
                    list = list.stream()
                            .filter(item ->
                                    item.getId()
                                            .equals(Integer.parseInt(eventCatID)))
                            .collect(Collectors.toList());
                }

                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Success", true, list);
            } else {
                return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category not exist", false, null);
            }
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false, null);
        }
    }
}
