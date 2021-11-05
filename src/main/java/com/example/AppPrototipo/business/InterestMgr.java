package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.persistence.InterestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestMgr {

    private final InterestRepository interestRepository;

    public InterestMgr(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public List<Interest> findAll(){
        return  interestRepository.findAll();
    }

}
