package com.example.AppPrototipo.business.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "operators")
@PrimaryKeyJoinColumn(name = "idoperator")
public class Operator extends User{

    public Operator() {
    }

    public Operator(String name, String username, String email, String password) {
        super(name, username, email, password);
    }
}
