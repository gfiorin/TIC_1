package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name="interests")
public class Interest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_interests")
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="type_of_experience")
    private ExperienceType experienceType;

    public Interest() {
    }

    public Interest(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
