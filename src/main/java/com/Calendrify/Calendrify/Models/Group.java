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
@Table(name = "`group`")
public class Group {
    @Id
    @Column(name = "groupID", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 20)
    private String name;

    @Column(name = "Description")
    private String description;

}