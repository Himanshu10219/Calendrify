package com.Calendrify.Calendrify.Models.BodyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
public class AddEventBody {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("startDateTime")
    private String startDateTime;
    @JsonProperty("endDateTime")
    private String endDateTime;
    @JsonProperty("online")
    private Boolean online;
    @JsonProperty("url")
    private String url;
    @JsonProperty("venueName")
    private String venueName;
    @JsonProperty("availability")
    private Boolean availability;
    @JsonProperty("hostID")
    private Integer hostID;
    @JsonProperty("eventCatID")
    private Integer eventCatID;
    @JsonProperty("isDeleted")
    private Boolean isDeleted;
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("startDateTime")
    public String getStartDateTime() {
        return startDateTime;
    }

    @JsonProperty("startDateTime")
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @JsonProperty("endDateTime")
    public String getEndDateTime() {
        return endDateTime;
    }

    @JsonProperty("endDateTime")
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @JsonProperty("online")
    public Boolean getOnline() {
        return online;
    }

    @JsonProperty("online")
    public void setOnline(Boolean online) {
        this.online = online;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("venueName")
    public String getVenueName() {
        return venueName;
    }

    @JsonProperty("venueName")
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @JsonProperty("availability")
    public Boolean getAvailability() {
        return availability;
    }

    @JsonProperty("availability")
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @JsonProperty("hostID")
    public Integer getHostID() {
        return hostID;
    }

    @JsonProperty("hostID")
    public void setHostID(Integer hostID) {
        this.hostID = hostID;
    }

    @JsonProperty("eventCatID")
    public Integer getEventCatID() {
        return eventCatID;
    }

    @JsonProperty("eventCatID")
    public void setEventCatID(Integer eventCatID) {
        this.eventCatID = eventCatID;
    }

    @JsonProperty("isDeleted")
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


}
