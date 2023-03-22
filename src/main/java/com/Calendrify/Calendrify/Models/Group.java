package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}