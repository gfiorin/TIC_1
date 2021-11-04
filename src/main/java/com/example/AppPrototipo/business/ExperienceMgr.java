package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceMgr {

    private final ExperienceRepository experienceRepository;

    public ExperienceMgr(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
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

}
