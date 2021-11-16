package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.managers.ExperienceTypeMgr;
import com.example.AppPrototipo.business.managers.InterestMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExperienceGridController implements Initializable {

    private final ExperienceMgr experienceMgr;
    private final UserMgr userMgr;
    private final TouristController touristController;
    private static Tourist tourist;
    private final InterestMgr interestMgr;
    private final ExperienceTypeMgr experienceTypeMgr;
    private Set<ExperienceType> types;

    @FXML
    private GridPane grillaRecomendaciones;

    public ExperienceGridController(ExperienceMgr experienceMgr, UserMgr userMgr, @Lazy TouristController touristController, InterestMgr interestMgr, ExperienceTypeMgr experienceTypeMgr) {
        this.experienceMgr = experienceMgr;
        this.userMgr = userMgr;
        this.touristController = touristController;
        this.interestMgr = interestMgr;
        this.experienceTypeMgr = experienceTypeMgr;
    }

    @Bean
    @Scope("prototype")
    private MiniExperienceController miniExperienceControllerPrototype() {
        return new MiniExperienceController(userMgr, touristController, experienceMgr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (Interest interest : tourist.getInterests()) { //touristMgr
            types.addAll(interest.getExperienceTypes());
        }

        for (Experience expLiked : tourist.getLiked()){
            types.addAll(expLiked.getExperienceTypes());
        }

        for (Experience bookings : tourist.getExperiencesBooked()){
            types.addAll(bookings.getExperienceTypes());
        }

        List<Experience> recommendations = recommendations();
        int columns = 0;
        int row = 1;

        try {
            for (Experience recommendation : recommendations) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceController miniExperienceController = (MiniExperienceController) applicationContext
                        .getBean("miniExperienceControllerPrototype");
                fxmlLoader.setController(miniExperienceController);

                fxmlLoader.setController(miniExperienceController);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(recommendation);

                if (columns == 4) {
                    columns = 0;
                    ++row;
                }

                grillaRecomendaciones.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Experience> recommendations() {
        List<Experience> recommendations = experienceMgr.findByTypes(new ArrayList<>(types));

        for (Experience experience : recommendations){
            if (tourist.getLiked().contains(experience) || tourist.getExperiencesBooked().contains(experience)){
                recommendations.remove(experience);
            } else {
                experience.setPonderation(weigh(experience));
            }
        }

        recommendations = recommendations.stream().sorted(Comparator.comparingInt(Experience::getPonderation)).collect(Collectors.toList());
        Collections.reverse(recommendations);

        return recommendations;
    }


    private Integer weigh(Experience experience){

        List<ExperienceType> expTypes = experience.getExperienceTypes();

        int n = 0;
        for (ExperienceType experienceType : expTypes){
            if (!types.contains(experienceType)) n++;
        }

        int s = expTypes.size() - n;

        int l = experience.getLikes().size();

        int r = experience.getBookings().size();

        return -10*n + 9*s + 8*l + 7*r;

    }


    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        ExperienceGridController.tourist = tourist;
    }

}
