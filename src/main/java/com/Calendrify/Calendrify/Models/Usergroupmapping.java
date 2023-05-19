package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usergroupmapping")
public class Usergroupmapping {
    @Id
    @Column(name = "mapID", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "groupID")
    private Usergroup groupID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userID")
    private User userID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usergroup getGroupID() {
        return groupID;
    }

    public void setGroupID(Usergroup groupID) {
        this.groupID = groupID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

}