package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.persistence.ExperienceTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceTypeMgr {

    private final ExperienceTypeRepository experienceTypeRepository;

    public ExperienceTypeMgr(ExperienceTypeRepository experienceTypeRepository) {
        this.experienceTypeRepository = experienceTypeRepository;
    }

    public ExperienceType findOneByName(String name){
        return experienceTypeRepository.findOneByName(name);
    }

    public void save(ExperienceType experienceType){
        experienceTypeRepository.save(experienceType);
    }

}
