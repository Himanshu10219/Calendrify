package com.Calendrify.Calendrify.Models.BodyResponse;

import java.util.List;
public class MailBody {
    private List<String> toEmail;
    private String title;
    private String description;
    private String date;
    private String startEndTime;
    private String location;

    public MailBody(List<String> toEmail, String title, String description, String date, String startEndTime, String location) {
        this.toEmail = toEmail;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startEndTime = startEndTime;
        if(!location.isEmpty() || location!=null){
            this.location = location;
        }else{
            this.location="";
        }

    }

    public List<String> getToEmail() {
        return toEmail;
    }

    public void setToEmail(List<String> toEmail) {
        this.toEmail = toEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
