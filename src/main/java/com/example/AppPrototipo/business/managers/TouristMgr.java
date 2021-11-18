package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristMgr {

    private final TouristRepository touristRepository;

    public TouristMgr(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<Tourist> findAll(){
        return touristRepository.findAll();
    }

    public List<Interest> getInterests(Tourist tourist) {
        return tourist.getInterests();
    }

    public List<Experience> getLiked(Tourist tourist) {
        return tourist.getLiked();
    }

    public List<Experience> getExperiencesBooked(Tourist tourist) {
        return tourist.getExperiencesBooked();
    }


}
