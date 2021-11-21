package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@PrimaryKeyJoinColumn(name = "id")
public class Department {

    @Id
    private Integer id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    public Department() {
    }

    @Override
    public String toString() {
        return this.departmentName;
    }

}
