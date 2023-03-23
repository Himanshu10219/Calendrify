package com.Calendrify.Calendrify.Models.BodyResponse;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EventCategoryBody {

    @JsonProperty("eventCatID")
    private Integer eventCatID;

    @JsonProperty("eventCatID")
    public Integer getEventCatID() {
        return eventCatID;
    }

    @JsonProperty("eventCatID")
    public void setEventCatID(Integer eventCatID) {
        this.eventCatID = eventCatID;
    }


}
