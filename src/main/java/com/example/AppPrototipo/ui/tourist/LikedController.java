package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LikedController implements Initializable {

    private final UserMgr userMgr;

    @FXML
    private VBox vBoxGral;

    @FXML
    private VBox vBoxExp;

    public LikedController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Tourist tourist = userMgr.getCurrentTourist();
        List<Experience> likedExperiences = tourist.getLiked();
        if(likedExperiences.size() == 0){
            Text text = new Text();
            text.setFill(Paint.valueOf("#6b6a6a"));
            text.setFont(Font.font("System", FontPosture.ITALIC, 20));
            text.setText("  No ha seleccionado ningún favorito por el momento");
            vBoxGral.getChildren().add(text);
        }

        int counter = 0;

        try {

            HBox hbox = new HBox();
            hbox.setSpacing(15);
            vBoxExp.getChildren().add(hbox);

            for (Experience likedExperience : likedExperiences) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceController miniExperienceController = (MiniExperienceController) applicationContext
                        .getBean("miniExperienceControllerPrototype");
                fxmlLoader.setController(miniExperienceController);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                VBox miniExeperience = fxmlLoader.load();
                miniExperienceController.setData(likedExperience, true);
                hbox.getChildren().add(miniExeperience);
                counter++;

                if (counter == 3) {
                    counter = 0;
                    hbox = new HBox();
                    hbox.setSpacing(15);
                    vBoxExp.getChildren().add(hbox);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
