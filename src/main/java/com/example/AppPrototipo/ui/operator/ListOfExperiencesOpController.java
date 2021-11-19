package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.OperatorMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.ui.tourist.MiniExperienceController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ListOfExperiencesOpController implements Initializable {

    private final OperatorMgr operatorMgr;

    @FXML
    private GridPane grillaExperiencias;

    public ListOfExperiencesOpController(OperatorMgr operatorMgr) {
        this.operatorMgr = operatorMgr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> experiences = new ArrayList<>(operatorMgr.getExperiences());
        int columns = 0;
        int row = 1;

        try {
            for (Experience exp : experiences) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceController miniExperienceController = (MiniExperienceController) applicationContext
                        .getBean("miniExperienceControllerPrototype");
                fxmlLoader.setController(miniExperienceController);

                fxmlLoader.setController(miniExperienceController);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(exp);

                if (columns == 4) {
                    columns = 0;
                    ++row;
                }

                grillaExperiencias.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    @Scope("prototype")
//    private MiniExperienceController miniExperienceControllerPrototype() {
//        return new MiniExperienceController(userMgr, touristController, experienceMgr);
//    }

}
