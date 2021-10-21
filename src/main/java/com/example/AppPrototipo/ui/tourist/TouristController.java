package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TouristController implements Initializable {

    private static Tourist tourist;

    @FXML
    private ImageView favoritosMarker;

    @FXML
    private GridPane grillaExperiencias;

    @FXML
    private ImageView perfilMarker;

    @FXML
    private ImageView sesionMarker;

    private final ExperienceRepository experienceRepository;

    public TouristController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        TouristController.tourist = tourist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> experiences = new ArrayList<>(recomendations());

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < experiences.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("MiniExperience.fxml"));
                MiniExperienceController.setTourist(tourist);
                VBox vbox = fxmlLoader.load();
                MiniExperienceController miniExperienceController = fxmlLoader.getController();
                miniExperienceController.setData(experiences.get(i));
                grillaExperiencias.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Experience> recomendations(){
        List<Experience> list = new ArrayList<>();
        for (int i=0; i<6; i++) {
            Experience experience = experienceRepository.findById(1);
            list.add(experience);
        }
        return list;
    }
}
