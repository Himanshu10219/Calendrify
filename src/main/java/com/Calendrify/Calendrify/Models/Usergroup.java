package com.Calendrify.Calendrify.Models;

import jakarta.persistence.*;
<<<<<<< HEAD
=======
import org.hibernate.annotations.Cascade;
>>>>>>> main
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usergroup")
public class Usergroup {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId", nullable = false)
=======
    @Column(name = "groupId", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)

>>>>>>> main
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
<<<<<<< HEAD
    @OnDelete(action = OnDeleteAction.CASCADE)
=======
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
>>>>>>> main
    @JoinColumn(name = "createBy", nullable = false)
    private User createBy;

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

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

}