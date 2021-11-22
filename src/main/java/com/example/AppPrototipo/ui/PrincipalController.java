package com.example.AppPrototipo.ui;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.entities.User;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.ui.admin.AdminController;
import com.example.AppPrototipo.ui.operator.OperatorController;
import com.example.AppPrototipo.ui.tourist.TouristController;
import com.example.AppPrototipo.ui.userCreation.UserCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;


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
    private final TouristController touristController;
    private final AdminController adminController;
    private final OperatorController operatorController;

    public PrincipalController(UserMgr userMgr, TouristController touristController, AdminController adminController, OperatorController operatorController) {
        this.userMgr = userMgr;
        this.touristController = touristController;
        this.adminController = adminController;
        this.operatorController = operatorController;
    }

    @FXML
    void agregarUsuarioAction(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(UserCreationController.class.getResourceAsStream("AddUser.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        oldStage.close();
    }

    @FXML
    void loginAction(ActionEvent actionEvent) throws IOException {

        if(userInput.getText() == null || userInput.getText().isEmpty()
            || passwordInput.getText() == null || passwordInput.getText().isEmpty()){
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
            return;
        }

        try {
            User user = userMgr.userLogIn(userInput.getText(), passwordInput.getText());

            if(user instanceof Administrator){
                Node source = (Node) actionEvent.getSource();
                Stage oldStage  = (Stage) source.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(adminController.getClass().getResourceAsStream("AdminPanel.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                oldStage.close();
            } else if (user instanceof Tourist){
                Node source = (Node) actionEvent.getSource();
                Stage oldStage  = (Stage) source.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(touristController.getClass().getResourceAsStream("TouristMain.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                oldStage.close();
            } else if (user instanceof Operator){
                Node source = (Node) actionEvent.getSource();
                Stage oldStage  = (Stage) source.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(operatorController.getClass().getResourceAsStream("OperatorMain.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                oldStage.close();
            }
        } catch (InvalidInformation invalidInformation) {
            showAlert("Datos ingresados erroneos!", invalidInformation.getMessage());
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
