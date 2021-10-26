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

    @Column(name = "shortdescription")
    private String shortdescription;

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
            joinColumns = {@JoinColumn(name = "experience")},
            inverseJoinColumns = {@JoinColumn(name = "type_of_experience")}
    )
    private List<ExperienceType> experienceTypes;

    @ManyToOne
    @JoinColumn(name="tour_operator")
    private TourOperator tourOperator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "experience")
    List<Image> images;

    @Column(name = "location")
    private String ubicacion;

    @Column(name = "reviewed")
    private Boolean reviewed;

    public Experience() {
    }

    public Experience(String title, String description, String shortdescription, boolean vaccination, Integer capacity, boolean bookable, boolean authorized, List<Image> images) {
        this.title = title;
        this.description = description;
        this.shortdescription = shortdescription;
        this.vaccination = vaccination;
        this.capacity = capacity;
        this.bookable = bookable;
        this.authorized = authorized;
        this.images = images;
        this.reviewed = false;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public List<ExperienceType> getExperienceTypes() {
        return experienceTypes;
    }

    public void setExperienceTypes(List<ExperienceType> experienceTypes) {
        this.experienceTypes = experienceTypes;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public void enableExperience(){
        this.authorized = true;
    }

    public void disableExperience(){
        this.authorized = false;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getShortDescription() { return shortdescription; }

    public void setShortDescription(String shortDescription) { this.shortdescription = shortDescription; }
}
