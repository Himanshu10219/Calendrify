package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}