package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "operators")
@PrimaryKeyJoinColumn(name = "id")
public class Operator extends User {

    @ManyToOne
    @JoinColumn(name="toperator")
    private TourOperator tourOperator;

    public Operator() {}

    public Operator(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public Operator(String name, String username, String email, String password, TourOperator tourOperator) {
        super(name, username, email, password);
        this.tourOperator=tourOperator;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }

}
