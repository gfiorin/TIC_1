package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ExperienceGridController implements Initializable {

    private Tourist tourist;

    private final ExperienceMgr experienceMgr;
    private final MiniExperienceController miniExperienceController;

    @FXML
    private GridPane grillaExperiencias;

    public ExperienceGridController(ExperienceMgr experienceMgr, MiniExperienceController miniExperienceController) {
        this.experienceMgr = experienceMgr;
        this.miniExperienceController = miniExperienceController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> experiences = new ArrayList<>(recomendations());

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < experiences.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                MiniExperienceController.setTourist(tourist);
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(experiences.get(i));
                grillaExperiencias.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    private List<Experience> recomendations(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<6; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }
}
