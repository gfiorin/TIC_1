package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "types_of_experiences")
public class ExperienceType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == ExperienceType.class) {
            ExperienceType e = (ExperienceType) o;
            return Objects.equals(e.getId(), id);
        } else return false;
    }

}
