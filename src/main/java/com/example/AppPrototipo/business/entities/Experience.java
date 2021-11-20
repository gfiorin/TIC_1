package com.example.AppPrototipo.business.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private String shortDescription;

    @Column(name = "vaccination")
    private boolean vaccination;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "bookable")
    private boolean bookable;

    @Column(name = "authorized")
    private boolean authorized;

    @LazyCollection(LazyCollectionOption.FALSE)
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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name="department")
    private Department department;

    @Column(name = "location")
    private String ubicacion;

    @Column(name = "email")
    private String email;

    @Column(name = "link")
    private String link;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "reviewed")
    private Boolean reviewed;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "liked",
            joinColumns = {@JoinColumn(name = "experience")},
            inverseJoinColumns = {@JoinColumn(name = "tourist")}
    )
    private List<Tourist> likes;

    public List<Tourist> getLikes() {
        return likes;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bookings",
            joinColumns = {@JoinColumn(name = "experience")},
            inverseJoinColumns = {@JoinColumn(name = "tourist")}
    )
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public Experience() {
    }

    public Experience(String title, String description, String shortDescription, boolean vaccination, Integer capacity, boolean bookable, boolean authorized, List<ExperienceType> experienceTypes, TourOperator tourOperator, Department department, String ubicacion, String email, String link, String telephone, Boolean reviewed, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.vaccination = vaccination;
        this.capacity = capacity;
        this.bookable = bookable;
        this.authorized = authorized;
        this.experienceTypes = experienceTypes;
        this.tourOperator = tourOperator;
        this.department = department;
        this.ubicacion = ubicacion;
        this.email = email;
        this.link = link;
        this.telephone = telephone;
        this.reviewed = reviewed;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
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

    public void setShortDescription(String shortdescription) {
        this.shortDescription = shortdescription;
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

    public String getEmail() {
        return email;
    }

    public String getLink() {
        return link;
    }

    public Experience(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return title;
    }

}
