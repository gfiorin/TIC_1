package com.example.AppPrototipo.ui.admin.creation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.managers.InterestMgr;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddInterestController {

    @FXML
    private Button goBackBtn;

    @FXML
    private Button addInterestBtn;

    @FXML
    private TextField nameInput;

    private final InterestMgr interestMgr;

    public AddInterestController(InterestMgr interestMgr) {
        this.interestMgr = interestMgr;
    }

    @FXML
    void addInterest(ActionEvent event) throws IOException {

        try {

            String name = nameInput.getText();

            interestMgr.addInterest(name);

            showAlert("Interés creado con éxito", "¡El interés ha sido creado con éxito!");

            goBack(event);

        } catch (InvalidInformation invalidInformation) {
            showAlert(
                    "¡Información inválida!",
                    invalidInformation.getMessage());
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AdminController.class.getResourceAsStream("AdminPanel.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
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
