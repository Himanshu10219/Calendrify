package com.Calendrify.Calendrify.Models.BodyResponse;

import java.util.List;
public class MailBody {
    private List<String> toEmail;
    private String date;
    private String title;
    private String hostBy;
    private String description;
    private String url;
    private String startEndTime;
    private String groupName;
    private String location;

    public MailBody(List<String> toEmail, String date, String title, String hostBy, String description, String url, String startEndTime, String groupName, String location) {
        this.toEmail = toEmail;
        this.date = date;
        this.title = title;
        this.hostBy = hostBy;
        this.description = description;
        this.url = url;
        this.startEndTime = startEndTime;
        this.groupName = groupName;
        this.location = location;
    }

    public List<String> getToEmail() {
        return toEmail;
    }

    public void setToEmail(List<String> toEmail) {
        this.toEmail = toEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHostBy() {
        return hostBy;
    }

    public void setHostBy(String hostBy) {
        this.hostBy = hostBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
