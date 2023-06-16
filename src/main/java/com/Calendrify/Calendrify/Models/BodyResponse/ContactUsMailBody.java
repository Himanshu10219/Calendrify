package com.Calendrify.Calendrify.Models.BodyResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContactUsMailBody {
    private String name;
    private String email;
    private String contact;
    private String complaintDate;
    private String message;

    public ContactUsMailBody(String name, String email, String contact, String message) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.message = message;
    }

    public String getComplaintDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();
        return dateFormat.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
