package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.persistence.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorMgr {

    private final AdministratorRepository administratorRepository;

    public AdministratorMgr(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public void addAdministrator() { //todo

    }

    public List<Administrator> findAll(){
        return administratorRepository.findAll();
    }

}
