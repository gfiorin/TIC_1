package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "tourist")
    private Tourist tourist;

    @ManyToOne
    @JoinColumn(name = "experience")
    private Experience experience;


}
