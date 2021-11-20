package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ExperienceGridController implements Initializable {

    private final ExperienceMgr experienceMgr;
    private final UserMgr userMgr;
    private final TouristController touristController;

    @FXML
    private GridPane grillaRecomendaciones;

    public ExperienceGridController(ExperienceMgr experienceMgr, UserMgr userMgr, @Lazy TouristController touristController) {
        this.experienceMgr = experienceMgr;
        this.userMgr = userMgr;
        this.touristController = touristController;
    }

    @Bean
    @Scope("prototype")
    private MiniExperienceController miniExperienceControllerPrototype() {
        return new MiniExperienceController(userMgr, touristController, experienceMgr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Experience> recommendations = new ArrayList<>(recommendations());
        Tourist tourist = userMgr.getCurrentTourist();

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
                miniExperienceController.setData(recommendation, isLikedByUser(tourist, recommendation));

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

    private List<Experience> recommendations(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<6; i++) {
            Experience experience = experienceMgr.findById(i);      //Algoritmo de recomendaciones
            list.add(experience);
        }
        return list;
    }

    private boolean isLikedByUser(Tourist tourist, Experience experience) {
        return tourist.getLiked().stream().mapToInt(Experience::getId).anyMatch(id -> id == experience.getId());
    }
}
