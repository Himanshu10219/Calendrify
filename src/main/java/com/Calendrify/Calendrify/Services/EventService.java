package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    @Autowired
    EventRepo eventRepo;
    public List<Event> getAllEvents(){
        List<Event> list=new ArrayList<>();
        list=eventRepo.findAll();
        if(!list.isEmpty()){
            return list;
        }
        return  null;
    }
}
