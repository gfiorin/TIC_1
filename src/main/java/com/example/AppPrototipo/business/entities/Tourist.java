package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tourists")
@PrimaryKeyJoinColumn(name = "id_tourist")
public class Tourist extends User{

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "document_type", nullable = true)
    private String documentType;

    @Column(name = "document_number", nullable = true)
    private String documentNumber;

    @ManyToOne()
    @JoinColumn(name = "country_of_birth")
    private Country country;

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, Country country, String documentType, String documentNumber) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.country = country;
    }

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, Country country) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.country = country;
    }

    public Tourist() {}

}
