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
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "commentID", nullable = false)
    private Integer id;

    @Column(name = "inviteID")
    private Integer inviteID;

    @Column(name = "response")
    private String response;

    @Column(name = "comments")
    private String comments;

}