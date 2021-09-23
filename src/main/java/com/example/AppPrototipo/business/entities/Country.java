package com.example.AppPrototipo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

}
