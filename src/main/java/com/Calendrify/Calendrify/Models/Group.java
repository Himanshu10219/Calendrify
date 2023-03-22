package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupID", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 20)
    private String name;

    @Column(name = "Description")
    private String description;

}