package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "eventID", nullable = false)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startDateTime")
    private Instant startDateTime;

    @Column(name = "endDateTime")
    private Instant endDateTime;

    @Column(name = "online")
    private Boolean online;

    @Column(name = "url")
    private String url;

    @Column(name = "VenueName", length = 20)
    private String venueName;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostID")
    private User hostID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventCatID")
    private Eventcategory eventCatID;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "lastModify")
    private LocalDate lastModify;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

}