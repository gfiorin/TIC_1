package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LikedController implements Initializable {

    private Tourist tourist;

    @FXML
    private GridPane grillaFavoritos;

    private final UserMgr userMgr;

    public LikedController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    @Override
    @Transactional
    public void initialize(URL location, ResourceBundle resources) {

        Tourist tourist = userMgr.getCurrentTourist();
        List<Experience> likedExperiences = tourist.getLiked();

        int columns = 0;
        int row = 1;

        try {
            for (Experience likedExperience : likedExperiences) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceController miniExperienceController = (MiniExperienceController) applicationContext
                        .getBean("miniExperienceControllerPrototype");
                fxmlLoader.setController(miniExperienceController);

                fxmlLoader.setController(miniExperienceController);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(likedExperience, true);

                if (columns == 4) {
                    columns = 0;
                    ++row;
                }

                grillaFavoritos.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
