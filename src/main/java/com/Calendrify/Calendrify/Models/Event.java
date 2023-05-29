package com.Calendrify.Calendrify.Models;

import com.Calendrify.Calendrify.Healpers.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "eventID")
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description")
    private String description;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "startDateTime")
    private LocalDateTime startDateTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "endDateTime")
    private LocalDateTime endDateTime;

    @Column(name = "online")
    private Boolean online;

    @Column(name = "url")
    private String url;

    @Column(name = "VenueName", length = 20)
    private String venueName;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "hostID")
    private User hostID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "groupid", nullable = false)
    private Usergroup groupid;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "eventCatID")
    private Eventcategory eventCatID;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "lastModify")
    private LocalDate lastModify;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public User getHostID() {
        return hostID;
    }

    public void setHostID(User hostID) {
        this.hostID = hostID;
    }

    public Usergroup getGroupid() {
        return groupid;
    }

    public void setGroupid(Usergroup groupid) {
        this.groupid = groupid;
    }

    public Eventcategory getEventCatID() {
        return eventCatID;
    }

    public void setEventCatID(Eventcategory eventCatID) {
        this.eventCatID = eventCatID;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastModify() {
        return lastModify;
    }

    public void setLastModify(LocalDate lastModify) {
        this.lastModify = lastModify;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}