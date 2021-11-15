package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.ExperienceTypeMgr;
import com.example.AppPrototipo.business.managers.InterestMgr;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class RecommendationsController {

    private Tourist tourist;
    private final ExperienceMgr experienceMgr;
    private final InterestMgr interestMgr;
    private final ExperienceTypeMgr experienceTypeMgr;

    public RecommendationsController(ExperienceMgr experienceMgr, InterestMgr interestMgr, ExperienceTypeMgr experienceTypeMgr) {
        this.experienceMgr = experienceMgr;
        this.interestMgr = interestMgr;
        this.experienceTypeMgr = experienceTypeMgr;
    }

    @FXML
    public void initialize(Tourist tourist) {
        this.tourist = tourist;
        //recommendations()

    }

    private List<Experience> recommendations() {
        List<Experience> recommendations = experienceMgr.findByTypes(experienceTypes());

        for (Experience experience : recommendations){
            if (tourist.getLiked().contains(experience) || tourist.getExperiencesBooked().contains(experience)){
                recommendations.remove(experience);
            } else {
                experience.setPonderation(weigh(experience));
            }
        }

        //recommendations.sort(ponderation);


        return recommendations;
    }

    private List<ExperienceType> experienceTypes(){
        List<ExperienceType> types = new ArrayList<>();

        for (Interest interest : tourist.getInterests()) {
            types.addAll(interest.getExperienceTypes());
        }

        for (Experience expLiked : tourist.getLiked()){
            types.addAll(expLiked.getExperienceTypes());
        }

        for (Experience bookings : tourist.getExperiencesBooked()){
            types.addAll(bookings.getExperienceTypes());
        }

        return types;

    }

    private Integer weigh(Experience experience){
        return 0;
    }
}
