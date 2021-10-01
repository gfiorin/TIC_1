package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "types_of_experiences")
public class ExperienceType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_types_of_experiences;

    @Column(name = "name")
    private String name;

    public ExperienceType() {}


    public ExperienceType(String name) {
        this.name = name;
    }

    public Integer getId_types_of_experiences() {
        return id_types_of_experiences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
