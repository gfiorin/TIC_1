package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "image")
    byte[] imageData;

    public Image() {
    }

    public Image(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
