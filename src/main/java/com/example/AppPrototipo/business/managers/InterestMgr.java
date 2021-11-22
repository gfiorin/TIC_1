package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.persistence.InterestRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterestMgr {

    private final InterestRepository interestRepository;

    @Transactional
    public Interest getCurrentInterest(int id) {
        Interest interest = interestRepository.findById(id).get();
        Hibernate.initialize(interest.getExperienceTypes());
        return interest;
    }

    public InterestMgr(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public List<Interest> findAll(){
        return  interestRepository.findAll();
    }

}
