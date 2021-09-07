package com.example.AppPrototipo.ui.user;

import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @FXML
    private TextField nombreInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button agregarBtn;

    UserMgr userMgr;

    public UserController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    @FXML
    void agregarUsuario(ActionEvent event){
        if (nombreInput.getText() == null || emailInput.getText() == null ||
            nombreInput.getText().equals("") || emailInput.getText().equals("")){
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else {

            try {

                String nombre = nombreInput.getText();
                String email = emailInput.getText();

                userMgr.addUser(nombre,"apellido","username",email,"password");

                showAlert("Usuario creado con exito", "El usuario ha sido creado con exito");

                close(event);

            } catch (UserAlreadyExsists e) {
                showAlert(
                        "Email ya registrado",
                        "Ya existe un usuario registrado con ese email.");
            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "Informacion invalida !",
                        "Se ha encontrado un error en los datos ingresados.");
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
