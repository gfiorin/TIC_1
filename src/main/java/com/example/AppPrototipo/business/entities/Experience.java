package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "")
    private String title;

    @Column(name = "")
    private String description;

    @Column(name = "")
    private boolean vaccination;

    @Column(name = "")
    private Integer capacity;

    @Column(name = "")
    private String title;

    @Column(name = "")
    private String title;



}
