package com.example.AppPrototipo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tourists")
public class Tourist extends User{

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "documentType", nullable = true)
    private String documentType;

    @Column(name = "documentNumber", nullable = true)
    private String documentNumber;

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, String documentType, String documentNumber) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public Tourist (String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone) {
        super(name, username, email, password);
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
    }

    public Tourist() {}

}
