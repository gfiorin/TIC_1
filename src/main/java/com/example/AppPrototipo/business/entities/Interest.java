package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name="interests")
public class Interest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    //@OneToMany
    //private List<ExperienceType> experienceTypes;

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
