package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.OperatorMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.ui.tourist.MiniExperienceController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class ListOfExperiencesOpController implements Initializable {

    private final UserMgr userMgr;

    @FXML
    private Text titulo;

    @FXML
    private VBox vBoxGral;

    @FXML
    private VBox vBoxExperiencias;

    public ListOfExperiencesOpController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Operator operator = (Operator) userMgr.getCurrentUser();
        TourOperator tourOperator = operator.getTourOperator();

        ArrayList<Experience> experiences = new ArrayList<>(tourOperator.getListOfExperiences());

        try {
            for (Experience experience : experiences) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniExperienceOpController miniExperienceOpController = (MiniExperienceOpController) applicationContext.getBean("miniExperienceOpController");
                fxmlLoader.setController(miniExperienceOpController);

                fxmlLoader.setLocation(miniExperienceOpController.getClass().getResource("MiniBookingView.fxml"));
                HBox hbox = fxmlLoader.load();
                miniExperienceOpController.setData(experience);
                vBoxExperiencias.getChildren().add(hbox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
