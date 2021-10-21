package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types_of_experiences")
public class ExperienceType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id") // es necesario? todo
    private Integer id;

    @Column(name = "name")
    private String name;

    public ExperienceType() {}

    public ExperienceType(String name) {
        this.name = name;
    }

    public ExperienceType(String name, Interest interest){
        this.name = name;
        this.interest=interest;
    }

    public ExperienceType(String name, List<Interest> interests) {
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @ManyToOne
    @JoinColumn(name="interest")
    private Interest interest;

}
