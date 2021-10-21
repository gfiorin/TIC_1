package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.TourOperator;
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
public class AuthorizeTourOperatorController {


    private final TourOperatorRepository tourOperatorRepository;

    public TextField companyNameInput;

    public Button habilitarBtn;

    public Button deshabilitarBtn;

    public Button cancelarBtn;


    public AuthorizeTourOperatorController(TourOperatorRepository tourOperatorRepository) {
        this.tourOperatorRepository = tourOperatorRepository;
    }


    @FXML
    public void authorize(ActionEvent event){
        enable(event, true);
    }

    @FXML
    public void disable(ActionEvent event){
        enable(event, false);
    }

    @FXML
    public void enable(ActionEvent event, boolean enable){

        String companyName = companyNameInput.getText();

        if (companyName.isEmpty()){
            showAlert("Faltan datos","Por favor, ingrese el nombre de un operador turístico");
        } else {
            TourOperator tourOperator = tourOperatorRepository.findOneByCompanyName(companyName);
            if (tourOperator == null) {
                showAlert("Operador turístico inexistente","No existe un operador turístico de nombre: " + companyName);
            } else {
                try {
                    tourOperator.setAuthorized(enable);

                    close(event);

                } catch (Exception ignored) {}
            }
        }
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
