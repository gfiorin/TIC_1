package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "operators")
@PrimaryKeyJoinColumn(name = "id_operator")
public class Operator extends User{

    @ManyToOne
    @JoinColumn(name="toperator")
    private TourOperator tourOperator;

    public Operator() {}

    public Operator(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

}
