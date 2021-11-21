package com.example.AppPrototipo.business.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tourists")
@PrimaryKeyJoinColumn(name = "id")
public class Tourist extends User{

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "cellphone", nullable = false)
    private String cellphone;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @ManyToOne()
    @JoinColumn(name = "country_of_birth", nullable = false)
    private Country country;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tourist_interests",
            joinColumns = {@JoinColumn(name = "tourist")},
            inverseJoinColumns = {@JoinColumn(name = "interest")}
    )
    private List<Interest> interests;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "liked",
            joinColumns = {@JoinColumn(name = "tourist")},
            inverseJoinColumns = {@JoinColumn(name = "experience")}
    )
    private List<Experience> liked;

    public List<Experience> getLiked() {
        return liked;
    }

    public void addLiked(Experience experience) {
        liked.add(experience);
    }

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, Country country, List<Interest> interests, String documentType, String documentNumber) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.country = country;
        this.interests = interests;
    }

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, Country country, List<Interest> interests) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.country = country;
        this.interests = interests;
    }

    public Tourist() {}

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Country getCountry() {
        return country;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    @Override
    public String toString() {
        return this.getEmail();
    }

}
