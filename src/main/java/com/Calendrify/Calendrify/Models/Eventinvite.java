package com.Calendrify.Calendrify.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
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

}