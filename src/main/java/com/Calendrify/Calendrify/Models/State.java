package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stateID", nullable = false)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryID")
    private Country countryID;

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

    public Country getCountryID() {
        return countryID;
    }

    public void setCountryID(Country countryID) {
        this.countryID = countryID;
    }

}