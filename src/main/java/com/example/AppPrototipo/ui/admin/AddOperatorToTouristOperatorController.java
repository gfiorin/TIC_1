package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.persistence.OperatorsRepository;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class AddOperatorToTouristOperatorController {

    private final OperatorsRepository operatorsRepository;
    private final TourOperatorRepository tourOperatorRepository;

    public AddOperatorToTouristOperatorController(OperatorsRepository operatorsRepository, TourOperatorRepository tourOperatorRepository) {
        this.operatorsRepository = operatorsRepository;
        this.tourOperatorRepository = tourOperatorRepository;
    }

    public TextField operatorNameInput;
    public TextField tourOperatorNameInput;

    public Button agregarBtn;
    public Button cancelarBtn;

    @FXML
    public void addOperator(ActionEvent event){

    }

    @FXML
    void close(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
