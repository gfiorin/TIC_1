package com.example.AppPrototipo.business.entities;


import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private String rating;

    @ManyToOne
    @JoinColumn(name = "tourist")
    private Tourist touristId;

    @ManyToOne
    @JoinColumn(name = "experience")
    private Experience experienceId;


}
