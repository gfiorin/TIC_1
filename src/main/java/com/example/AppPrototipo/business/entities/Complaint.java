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

    @Column(name = "tourist_id")
    private Integer touristId;

    @Column(name = "experience_id")
    private Integer experienceId;





}
