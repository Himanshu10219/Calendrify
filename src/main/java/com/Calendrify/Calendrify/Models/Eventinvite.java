package com.Calendrify.Calendrify.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "eventinvite")
public class Eventinvite {
    @Id
    @Column(name = "inviteID", nullable = false)
    private Integer id;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "eventID")
    private Integer eventID;

    @Column(name = "inviteTime")
    private Instant inviteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Instant getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Instant inviteTime) {
        this.inviteTime = inviteTime;
    }

}