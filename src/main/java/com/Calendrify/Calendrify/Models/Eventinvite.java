package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "eventinvite")
public class Eventinvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inviteID", nullable = false)
    private Integer id;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "eventID")
    private Integer eventID;

    @Column(name = "inviteTime")
    private Instant inviteTime;

}