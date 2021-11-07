package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
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

    private final ExperienceMgr experienceMgr;
    private final MiniExperienceController miniExperienceController;
    private final UserMgr userMgr;

    @FXML
    private GridPane grillaRecomendaciones;

    public ExperienceGridController(ExperienceMgr experienceMgr, MiniExperienceController miniExperienceController, UserMgr userMgr) {
        this.experienceMgr = experienceMgr;
        this.miniExperienceController = miniExperienceController;
        this.userMgr = userMgr;
    }

//    @Transactional
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> recommendations = new ArrayList<>(recommendations());
        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < recommendations.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                //MiniExperienceController.setTourist(tourist);
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(recommendations.get(i));

                if(columns == 4){
                    columns = 0;
                    ++row;
                }

                grillaRecomendaciones.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));}
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
}
