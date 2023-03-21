package com.Calendrify.Calendrify.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usergroupmapping")
public class Usergroupmapping {
    @Id
    @Column(name = "mapID", nullable = false)
    private Integer id;

    @Column(name = "groupID")
    private Integer groupID;

    @Column(name = "userID")
    private Integer userID;

}