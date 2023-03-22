package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usergroupmapping")
public class Usergroupmapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapID", nullable = false)
    private Integer id;

    @Column(name = "groupID")
    private Integer groupID;

    @Column(name = "userID")
    private Integer userID;

}