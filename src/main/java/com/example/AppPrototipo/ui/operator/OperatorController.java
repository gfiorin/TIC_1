package com.example.AppPrototipo.ui.operator;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class OperatorController {

    @FXML
    private JFXButton boton_1;

    public OperatorController() {}

    @FXML
    void close(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // creo que no me anda por que el css esta mal ubicado https://stackoverflow.com/questions/36934339/javafx-warning-resource-style-css-not-found-intellij

}
