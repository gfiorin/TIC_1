package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "experiences")
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

    @ManyToMany
    @JoinTable(
            name = "types_experiences",
            joinColumns = {@JoinColumn(name = "id_experience")},
            inverseJoinColumns = {@JoinColumn(name = "id_type_of_experience")}
    )
    private List<ExperienceType> experienceTypes;

    @ManyToOne
    @JoinColumn(name="id_tourist_operator")
    private TourOperator tourOperator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_experience")
    List<Image> images;

    public Experience() {
    }

    public Experience(String title, String description, boolean vaccination, Integer capacity, boolean bookable, boolean authorized, List<Image> images) {
        this.title = title;
        this.description = description;
        this.vaccination = vaccination;
        this.capacity = capacity;
        this.bookable = bookable;
        this.authorized = authorized;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVaccination() {
        return vaccination;
    }

    public void setVaccination(boolean vaccination) {
        this.vaccination = vaccination;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
