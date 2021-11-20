package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.ExperienceRepository;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LikedController implements Initializable {

    private Tourist tourist;

    @FXML
    private GridPane grillaFavoritos;

    @FXML
    private Text sinFavoritos;

    private final ExperienceRepository experienceRepository;
    private final MiniExperienceController miniExperienceController;
    private final UserMgr userMgr;

    public LikedController(ExperienceRepository experienceRepository, MiniExperienceController miniExperienceController, UserMgr userMgr) {
        this.experienceRepository = experienceRepository;
        this.miniExperienceController = miniExperienceController;
        this.userMgr = userMgr;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sinFavoritos.setVisible(false);
        Tourist tourist = userMgr.getCurrentTourist();
        List<Experience> likes = tourist.getLiked();
        if(likes.size() == 0){
            sinFavoritos.setVisible(true);
        }

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < likes.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                MiniExperienceController.setTourist(tourist);
                fxmlLoader.setController(miniExperienceController);
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(likes.get(i));

                if(columns == 4){
                    columns = 0;
                    ++row;
                }

                grillaFavoritos.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
