package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Models.Eventinvite;
import com.Calendrify.Calendrify.Repository.InviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventInviteService {

    @Autowired
    InviteRepo inviteRepo;

    public List<Eventinvite> getalleventinvite()
    {
        List<Eventinvite> list = new ArrayList<>();
        list = inviteRepo.findAll();
        if(!list.isEmpty()){
            return list;
        }
        return  null;
    }

    public List<Eventinvite> geteventinviteById(int id)
    {
        Eventinvite list ;
        list = inviteRepo.findById(id).get();
        
        return  null;
    }
}
