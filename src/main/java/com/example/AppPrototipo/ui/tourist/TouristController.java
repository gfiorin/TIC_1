package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TouristController implements Initializable {

    @FXML
    private ImageView favoritosMarker;

    @FXML
    private GridPane grillaExperiencias;

    @FXML
    private ImageView perfilMarker;

    @FXML
    private ImageView sesionMarker;

    private final ExperienceMgr experienceMgr;

    public TouristController(ExperienceMgr experienceMgr) {
        this.experienceMgr = experienceMgr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> experiences = new ArrayList<>(experiences());

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < experiences.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                MiniExperienceController miniExperienceController = fxmlLoader.getController();
                miniExperienceController.setData(experiences.get(i));

                if(columns == 4){
                    columns = 0;
                    ++row;
                }

                grillaExperiencias.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Experience> experiences(){
        List<Experience> list = new ArrayList<>();
        for (int i=0; i<15; i++) {
            Experience experience = experienceMgr.findOneById(1);
            list.add(experience);
        }
        return list;
    }

}
