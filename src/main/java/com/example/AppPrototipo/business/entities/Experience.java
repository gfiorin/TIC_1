package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "vaccination")
    private boolean vaccination;

    @Column(name = "capacity")
    private Integer capacity;

    //@Column(name = "")
    //private String image;

    @Column(name = "bookable")
    private boolean bookable;

    @Column(name = "authorized")
    private boolean authorized;

}
