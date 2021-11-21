package com.example.AppPrototipo.business.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
@PrimaryKeyJoinColumn(name = "id")
public class Administrator extends User{

    public Administrator() {
    }

    public Administrator(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Administrator){
            Administrator administrator = (Administrator) obj;
            return this.getId().equals(administrator.getId());
        }
        return false;
    }

}
