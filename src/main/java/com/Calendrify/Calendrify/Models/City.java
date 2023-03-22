package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityID", nullable = false)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateID")
    private State stateID;

    @OneToMany(mappedBy = "cityID")
    private Set<Useraddress> useraddresses = new LinkedHashSet<>();

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

    public State getStateID() {
        return stateID;
    }

    public void setStateID(State stateID) {
        this.stateID = stateID;
    }

    public Set<Useraddress> getUseraddresses() {
        return useraddresses;
    }

    public void setUseraddresses(Set<Useraddress> useraddresses) {
        this.useraddresses = useraddresses;
    }

}