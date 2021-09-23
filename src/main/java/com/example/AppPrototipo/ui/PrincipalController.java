package com.example.AppPrototipo.ui;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.ui.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class PrincipalController {

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button agregarUsuario;

    @FXML
    private Button login;

    private final UserMgr userMgr;

    public PrincipalController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    @FXML
    void agregarUsuarioAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(UserController.class.getResourceAsStream("AddUser.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void loginAction(ActionEvent actionEvent) {

        if(userInput.getText() == null || userInput.getText().isEmpty()
            || passwordInput.getText() == null || passwordInput.getText().isEmpty()){
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
            return;
        }

        try {
            userMgr.userLogIn(userInput.getText(), passwordInput.getText());
        } catch (InvalidInformation invalidInformation) {
            invalidInformation.printStackTrace();
        }
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
