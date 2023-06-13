package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Exceptions.ResourceNotFoundException;
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

    public ResponseEntity<ResponseHandler> getAllEventsCategory(String eventCatID, String userID) {
        try {
            List<Eventcategory> list = eventCategoryRepo.findAll();
            if (!list.isEmpty()) {
                if (userID != null) {
                    list = list.stream()
                            .filter(item ->
                                    item.getCreatedBy().getId()
                                            .equals(Integer.parseInt(userID))||item.getCreatedBy().getId()
                                            .equals(6))
                            .collect(Collectors.toList());
                }
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

    public ResponseEntity<ResponseHandler> addEventCategory(Eventcategory ev) {
        try {
            eventCategoryRepo.save(ev);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category Added successfully", true,ev);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<ResponseHandler> deleteEventCategory(int id) {
        try {
            eventCategoryRepo.deleteById(id);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category Deleted successfully", true);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        }
    }

    public ResponseEntity<ResponseHandler> updateEventCategory(int id, Eventcategory eventcategory) {
        try {
            Eventcategory lastEventcategory = null;
            lastEventcategory = eventCategoryRepo.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Event Category not Found"));

            lastEventcategory.setName(eventcategory.getName() == null ? lastEventcategory.getName() : eventcategory.getName());
            lastEventcategory.setCreatedAt(eventcategory.getCreatedAt() == null ? lastEventcategory.getCreatedAt() : eventcategory.getCreatedAt());
            eventCategoryRepo.save(lastEventcategory);
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category Updated!!", true, lastEventcategory);
        } catch (Exception e) {
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Event Category not Updated!!" + e.getMessage(), true);
        }
    }
}
