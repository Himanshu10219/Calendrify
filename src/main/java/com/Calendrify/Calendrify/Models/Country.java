package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryID", nullable = false)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "countryID")
    private Set<State> states = new LinkedHashSet<>();

}