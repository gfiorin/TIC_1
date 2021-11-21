package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.*;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private final InterestMgr interestMgr;
    private final TouristController touristController;

    @FXML
    private Text titulo;

    @FXML
    private VBox vBoxGral;

    @FXML
    private GridPane grillaRecomendaciones;

    public ExperienceGridController(ExperienceMgr experienceMgr, UserMgr userMgr, InterestMgr interestMgr, @Lazy TouristController touristController) {
        this.experienceMgr = experienceMgr;
        this.userMgr = userMgr;
        this.interestMgr = interestMgr;
        this.touristController = touristController;
    }

    @Bean
    @Scope("prototype")
    private MiniExperienceController miniExperienceControllerPrototype() {
        return new MiniExperienceController(userMgr, touristController, experienceMgr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Set<ExperienceType> types = new HashSet<>();
        Tourist tourist = userMgr.getCurrentTourist();

        for (Interest interest : tourist.getInterests()) {
            interest = interestMgr.getCurrentInterest(interest.getId());
            types.addAll(interest.getExperienceTypes());
        }

        for (Experience expLiked : tourist.getLiked()) {
            expLiked = experienceMgr.getCurrentExperience(expLiked.getId());
            types.addAll(expLiked.getExperienceTypes());
        }

        for (Experience booking : tourist.getExperiencesBooked()) {
            booking = experienceMgr.getCurrentExperience(booking.getId());
            types.addAll(booking.getExperienceTypes());
        }

        List<Experience> recommendations = recommendations(tourist, types);

        int columns = 0;
        int row = 1;

        try {
            for (Experience recommendation : recommendations) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceController miniExperienceController = (MiniExperienceController) applicationContext
                        .getBean("miniExperienceControllerPrototype");
                fxmlLoader.setController(miniExperienceController);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(recommendation, false);

                if (columns == 4) {
                    columns = 0;
                    ++row;
                }

                grillaRecomendaciones.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Experience> recommendations(Tourist tourist, Set<ExperienceType> types) {

        List<Experience> experiences = experienceMgr.findByTypes(new ArrayList<>(types));
        List<ExperienceSort> recommendationSort = new ArrayList<>();

        for (Experience experience : experiences){
            if (experience.isAuthorized() &&
                    experience.getTourOperator().isAuthorized() &&
                    !tourist.getLiked().contains(experience) &&
                    !tourist.getExperiencesBooked().contains(experience)) {
                recommendationSort.add(new ExperienceSort(experience, weigh(experience,types)));
            }
        }

        recommendationSort = recommendationSort.stream().sorted(Comparator.comparingInt(ExperienceSort::getPonderation)).collect(Collectors.toList());
        Collections.reverse(recommendationSort);

        List<Experience> recommendations = new ArrayList<>();

        for (ExperienceSort sort : recommendationSort){
            recommendations.add(sort.getExperience());
        }

        return recommendations;
    }

    public static class ExperienceSort {
        private final Experience experience;
        private final int ponderation;

        public ExperienceSort(Experience experience, int ponderation) {
            this.experience = experience;
            this.ponderation = ponderation;
        }

        public Experience getExperience() {
            return experience;
        }

        public int getPonderation() {
            return ponderation;
        }

    }

    private Integer weigh(Experience experience, Set<ExperienceType> types){

        experience = experienceMgr.getCurrentExperience(experience.getId());

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

}
