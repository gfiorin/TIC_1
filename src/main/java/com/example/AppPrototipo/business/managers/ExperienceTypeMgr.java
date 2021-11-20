package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.persistence.ExperienceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ExperienceType> findAll(){
        return experienceTypeRepository.findAll();
    }

}
