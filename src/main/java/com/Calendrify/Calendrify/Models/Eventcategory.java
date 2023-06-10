package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Entity
@Table(name = "eventcategory")
public class Eventcategory {
    @Id
    @Column(name = "eventCatID")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "createdby")
    private User createdBy;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}