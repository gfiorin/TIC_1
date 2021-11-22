package com.example.AppPrototipo.ui.admin.creation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.business.managers.AdministratorMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AddAdministratorController {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button acceptBtn;

    private final AdminController adminController;

    private final AdministratorMgr administratorMgr;

    public AddAdministratorController(AdminController adminController, AdministratorMgr administratorMgr) {
        this.adminController = adminController;
        this.administratorMgr = administratorMgr;
    }

    @FXML
    void addAdministrator(ActionEvent event) throws IOException {

        List<Control> controlList = Arrays.asList(nameInput, emailInput, usernameInput, passwordInput);
        boolean invalidValue = false;
        for (Control control : controlList) {
            if (((TextField) control).getText() == null || ((TextField) control).getText().isBlank()) {
                invalidValue = true;
                break;
            }
        }

        if (invalidValue) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        }
        else {

            try {

                String name = nameInput.getText();
                String email = emailInput.getText();
                String user = usernameInput.getText();
                String password = passwordInput.getText();

                administratorMgr.addAdministrator(name, user, email, password);

                showAlert("Administrador creado con exito", "El administrador ha sido creado con éxito");

                //lo mando de vuelta a la vista principal
                Node source = (Node) event.getSource();
                Stage oldStage  = (Stage) source.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(adminController.getClass().getResourceAsStream("AdminPanel.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                oldStage.close();

            } catch (UserAlreadyExsists e) {
                showAlert(
                        "Email o usuario existente!",
                        e.getMessage());
            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "Informacion invalida!",
                        invalidInformation.getMessage());
            }

        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(adminController.getClass().getResourceAsStream("AdminPanel.fxml"));
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
