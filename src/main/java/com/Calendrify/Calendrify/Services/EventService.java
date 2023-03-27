package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepo eventRepo;

    public ResponseEntity<?> getAllEvents(){
        List<Event> list;
        list=eventRepo.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
    }
    public ResponseEntity<?> getEventById(int id){
        try {
            Event event=eventRepo.findById(id).get();
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception e) {
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

    public ResponseEntity<?> getEventByDate(String startDate,String endDate){
        List<Event> list=new ArrayList<>();

        list=eventRepo.getEventByDate(startDate, endDate);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
    }
    public ResponseEntity<?> getEventByCategory(int eventCatID){
        List<Event> list=new ArrayList<>();
        list=eventRepo.getEventByCategory(eventCatID);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>("No Event Added", HttpStatus.OK);
    }
}
