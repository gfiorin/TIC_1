package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "image")
    Byte[] imageData;

    public Image() {
    }

    public Image(Byte[] imageData) {
        this.imageData = imageData;
    }
}
