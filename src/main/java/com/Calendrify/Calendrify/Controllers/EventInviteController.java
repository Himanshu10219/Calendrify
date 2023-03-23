package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Models.Eventinvite;
import com.Calendrify.Calendrify.Services.EventInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping("/eventsinvite")
public class EventInviteController {

    @Autowired
    EventInviteService eventInviteService;

    @GetMapping("/getalleventinvite")

    public List<Eventinvite> getalleventinvite(){

        return eventInviteService.getalleventinvite();
    }

}
