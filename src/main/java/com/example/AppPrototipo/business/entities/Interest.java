package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "interests")
public class Interest {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

}
