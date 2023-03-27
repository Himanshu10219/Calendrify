package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "commentID", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "inviteID")
    private Integer inviteID;

    @Column(name = "response")
    private String response;

    @Column(name = "comments")
    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInviteID() {
        return inviteID;
    }

    public void setInviteID(Integer inviteID) {
        this.inviteID = inviteID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}