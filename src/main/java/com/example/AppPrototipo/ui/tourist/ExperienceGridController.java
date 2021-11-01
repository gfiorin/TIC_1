package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private GridPane grillaInteres1;

    @FXML
    private GridPane grillaInteres2;

    @FXML
    private GridPane grillaInteres3;

    @FXML
    private GridPane grillaInteres4;

    @FXML
    private GridPane grillaRecomendaciones;

    @FXML
    private Text interes1;

    @FXML
    private Text interes2;

    @FXML
    private Text interes3;

    @FXML
    private Text interes4;

    public ExperienceGridController(ExperienceMgr experienceMgr, MiniExperienceController miniExperienceController, UserMgr userMgr) {
        this.experienceMgr = experienceMgr;
        this.miniExperienceController = miniExperienceController;
        this.userMgr = userMgr;
    }

//    @Transactional
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Interest> interests = userMgr.getCurrentUserInterests();
        /*for(Interest interest : interests){
        }*/

//        interes1.setText(interests.get(0).getName());
//        interes2.setText(interests.get(1).getName());
//        interes3.setText(interests.get(2).getName());
//        interes4.setText(interests.get(3).getName());

        ArrayList<Experience> recomendations = new ArrayList<>(recomendations());
        ArrayList<Experience> interes1 = new ArrayList<>(interes1());
        ArrayList<Experience> interes2 = new ArrayList<>(interes2());
        ArrayList<Experience> interes3 = new ArrayList<>(interes3());
        ArrayList<Experience> interes4 = new ArrayList<>(interes4());

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < recomendations.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(recomendations.get(i));
                grillaRecomendaciones.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i=0; i < interes1.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(interes1.get(i));
                grillaInteres1.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i=0; i < interes2.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(interes2.get(i));
                grillaInteres2.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i=0; i < interes3.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(interes3.get(i));
                grillaInteres3.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i=0; i < interes4.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(interes4.get(i));
                grillaInteres4.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Experience> recomendations(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<6; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }

    private List<Experience> interes1(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<6; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }

    private List<Experience> interes2(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<4; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }

    private List<Experience> interes3(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<5; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }

    private List<Experience> interes4(){
        List<Experience> list = new ArrayList<>();
        for (int i=1; i<2; i++) {
            Experience experience = experienceMgr.findById(i);
            list.add(experience);
        }
        return list;
    }
}
