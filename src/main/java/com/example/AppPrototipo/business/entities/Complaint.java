package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "tourist")
    private Tourist touristId;

    @ManyToOne
    @JoinColumn(name = "experience")
    private Experience experienceId;





}
