package com.example.AppPrototipo.ui.tourist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class TouristController {

    @FXML
    AnchorPane apMain;

    @FXML
    private Button experiencia1;

    @FXML
    void experienciaAction(ActionEvent actionEvent) throws IOException {
        Parent experienceView = FXMLLoader.load(getClass().getResource("ExperienceView.fxml"));
        Scene experienceScene = new Scene(experienceView);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(experienceScene);
        stage.show();
    }

}
