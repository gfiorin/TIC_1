package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    public static final List<Time> availableTimes = Arrays.asList(
            Time.valueOf("08:00:00"),
            Time.valueOf("09:00:00"),
            Time.valueOf("10:00:00"),
            Time.valueOf("11:00:00"),
            Time.valueOf("12:00:00"),
            Time.valueOf("13:00:00"),
            Time.valueOf("14:00:00"),
            Time.valueOf("15:00:00"),
            Time.valueOf("16:00:00"),
            Time.valueOf("17:00:00")
    );

    @Id
    private int id;

    @OneToOne()
    @JoinColumn(name = "experience", referencedColumnName = "id")
    private Experience experience;

    @ManyToOne()
    @JoinColumn(name = "tourist", referencedColumnName = "id")
    private Tourist tourist;

    @Basic
    @Column(name = "date")
    private Date date;

    @Basic
    @Column(name = "time")
    private Time time;

    @Column(name = "amount")
    private int amount;

    public Booking() {
    }

    public Booking(Experience experience, Tourist tourist, Date date, Time time, int amount) {
        this.experience = experience;
        this.tourist = tourist;
        this.date = date;
        this.time = time;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Experience getExperience() {
        return experience;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
