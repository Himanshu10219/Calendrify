package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Integer id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile", length = 13)
    private String mobile;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "hostID")
    private Set<Event> events = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userID")
    private Set<Useraddress> useraddresses = new LinkedHashSet<>();

}