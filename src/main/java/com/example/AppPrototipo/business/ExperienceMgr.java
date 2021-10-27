package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceMgr {

    private final ExperienceRepository experienceRepository;

    public ExperienceMgr(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience findById(int id){
        return experienceRepository.findById(id);
    }
}
