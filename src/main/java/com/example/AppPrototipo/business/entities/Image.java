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

    @ManyToOne
    @JoinColumn(name="experience")
    private Experience experience;

    public Image(byte[] imageData, Experience experience) {
        this.imageData = imageData;
        this.experience = experience;
    }

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

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
