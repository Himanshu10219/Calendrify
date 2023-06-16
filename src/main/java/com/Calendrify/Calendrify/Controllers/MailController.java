package com.Calendrify.Calendrify.Controllers;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.BodyResponse.ContactUsMailBody;
import com.Calendrify.Calendrify.Models.BodyResponse.MailBody;
import com.Calendrify.Calendrify.Services.EmailSenderService;
import com.Calendrify.Calendrify.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MailController {

    @Autowired
    EmailSenderService emailSenderService;
    @PostMapping("mail/send")
    public ResponseEntity<ResponseHandler> sendMail(@RequestBody MailBody request) {
        return emailSenderService.sendSimpleEmail(request);
    }

    @PostMapping("mail/sendComplaint")
    public ResponseEntity<ResponseHandler> sendComplaintMail(@RequestBody ContactUsMailBody request) {
        return emailSenderService.sendContactUsEmail(request);
    }
}
