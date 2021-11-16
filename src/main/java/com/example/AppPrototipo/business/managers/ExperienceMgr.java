package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.BookingRepository;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
public class ExperienceMgr {

    private final ExperienceRepository experienceRepository;
    private final BookingRepository bookingRepository;

    public ExperienceMgr(ExperienceRepository experienceRepository, BookingRepository bookingRepository) {
        this.experienceRepository = experienceRepository;
        this.bookingRepository = bookingRepository;
    }

    public Experience findById(int id) throws NoSuchElementException {
        try {
            return experienceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private int remainingCapacityForExperienceOnDateAtTime(Experience experience, Date date, Time time){
        List<Booking> bookings = bookingRepository.findByExperienceAndDateAndTimeOrderByTimeAsc(experience, date, time);
        return experience.getCapacity() - bookings.stream().mapToInt(Booking::getAmount).sum();
    }

    public Map<Time, Integer> remainingCapacityForExperienceOnDate(Experience experience, Date date){
        Map<Time, Integer> timesMap = new LinkedHashMap<>();

        for (Time time : Booking.availableTimes){
            timesMap.put(time, remainingCapacityForExperienceOnDateAtTime(experience, date, time));
        }

        return timesMap;
    }

    public boolean isRemainingCapacityForExperienceOnDate(Experience experience, Date date){
        for (Time time : Booking.availableTimes){
            if (remainingCapacityForExperienceOnDateAtTime(experience, date, time) > 0){
                return true;
            }
        }
        return false;
    }

    public void createNewReservation(Experience experience, Tourist tourist, Date date, Time time, int amount){
        this.bookingRepository.save(new Booking(experience, tourist, date, time, amount));
    }

    @Transactional
    public void changeAuthorizationOfExperience(Integer experienceId){
        Experience experience = experienceRepository.findOneById(experienceId);
        if (experience.isAuthorized()){
            experience.disableExperience();
        }
        else {
            experience.enableExperience();
        }
    }

    public List<Experience> findAll(){
        return experienceRepository.findAll();
    }

    public Experience findOneById(int id){
        return experienceRepository.findOneById(id);
    }

    public List<Experience> findByTypes(List<ExperienceType> experienceTypes){
        return experienceRepository.findAllByExperienceTypesContains(experienceTypes);
    }

}
