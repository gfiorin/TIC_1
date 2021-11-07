package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    List<Booking> findByExperienceAndDateAndTimeOrderByTimeAsc(Experience experience, Date date, Time time);

}
