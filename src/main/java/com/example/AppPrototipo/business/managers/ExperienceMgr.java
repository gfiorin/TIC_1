package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.*;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.persistence.BookingRepository;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class ExperienceMgr {

    private final ExperienceRepository experienceRepository;
    private final BookingRepository bookingRepository;

    public ExperienceMgr(ExperienceRepository experienceRepository, BookingRepository bookingRepository) {
        this.experienceRepository = experienceRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findByExperience(Experience experience){
        return bookingRepository.findByExperience(experience);
    }

    public Experience findById(int id){
        return experienceRepository.findById(id).get();
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

    @Transactional
    public Experience getCurrentExperience(int id){
        Experience experience = experienceRepository.findById(id).get();
        Hibernate.initialize(experience.getExperienceTypes());
        Hibernate.initialize(experience.getBookings());
        Hibernate.initialize(experience.getImages());
        return experience;
    }

    public List<Experience> findAll(){
        return experienceRepository.findAll();
    }

    public Experience findOneById(int id){
        return experienceRepository.findOneById(id);
    }

    @Transactional
    public List<Experience> findByTypes(List<ExperienceType> experienceTypes){

        List<Experience> allExperiences = experienceRepository.findAll();

        List<Experience> result = new ArrayList<>();

        for (Experience e : allExperiences) {

            List<ExperienceType> types = e.getExperienceTypes();

            boolean ok = false;

            for (ExperienceType t : types) {
                if (experienceTypes.contains(t)){
                    ok = true;
                    break;
                }
            }

            if (ok) result.add(e);
        }

        return result;
    }


    public void updateExperience(Experience experience){
        experienceRepository.save(experience);
    }

    public List<Experience> findByTourOperator(TourOperator tourOperator){
        return experienceRepository.findByTourOperator(tourOperator);
    }

    @Transactional
    public void addExperience(String title, String description, String shortDescription, boolean vaccination, Integer capacity, BigDecimal price, boolean bookable, List<ExperienceType> experienceTypes, TourOperator tourOperator, Department department, String ubicacion, String email, String link, String telephone, List<byte[]> images) throws InvalidInformation {

        if (title == null || title.isBlank() || title.length() > 30){
            throw new InvalidInformation("Por favor ingrese un titulo válido");
        }

        if (shortDescription == null || shortDescription.isBlank()){
            throw new InvalidInformation("Por favor ingrese una descripción corta válida");
        }

        if (description == null || description.isBlank()){
            throw new InvalidInformation("Por favor ingrese una descripción válida");
        }

        if (department == null){
            throw new InvalidInformation("Por favor seleccione un departamento");
        }

        if (ubicacion == null || ubicacion.isBlank()){
            throw new InvalidInformation("Por favor ingrese un titulo válido");
        }

        if (capacity != null && capacity < 0){
            throw new InvalidInformation("Por favor ingrese una cantidad de personas válida");
        }

        if (price != null && price.compareTo(new BigDecimal(0)) < 0){
            throw new InvalidInformation("Por favor ingrese un precio válido");
        }

        if (telephone == null || telephone.isBlank()){
            throw new InvalidInformation("Por favor ingrese un télefono válido");
        }

        if (email == null || email.isBlank()){
            throw new InvalidInformation("Por favor ingrese un email válido");
        }

        if (link == null || link.isBlank()){
            throw new InvalidInformation("Por favor ingrese un link válido");
        }

        if (experienceTypes.size() == 0){
            throw new InvalidInformation("Por favor asocie esta experiencia a por lo menos un tipo de experiencia");
        }

        if (experienceTypes.size() > 5){
            throw new InvalidInformation("Solo puede asociar un maximo de 5 experiencias");
        }

        if (images.size() == 0){
            throw new InvalidInformation("La experiencia debe tener al menos una imagen asociada");
        }

        Experience experienceToAdd = new Experience(title, description, shortDescription, vaccination, capacity, bookable, false, experienceTypes, tourOperator, department, ubicacion, email, link, telephone, true, price);

        experienceRepository.save(experienceToAdd);

        List<Image> imageList = new LinkedList<>();

        for (byte[] image : images){
            imageList.add(new Image(image, experienceToAdd));
        }

        experienceToAdd.setImages(imageList);
    }

}