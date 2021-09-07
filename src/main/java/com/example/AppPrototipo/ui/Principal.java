package com.example.AppPrototipo.ui;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.ui.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class Principal {

    @FXML
    private MenuItem agregarUsuario;

    @FXML
    void agregarUsuarioAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(UserController.class.getResourceAsStream("AddClient.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
