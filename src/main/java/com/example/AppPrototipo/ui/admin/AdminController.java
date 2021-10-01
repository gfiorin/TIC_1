package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.ui.PrincipalController;
import com.example.AppPrototipo.ui.userCreation.UserCreationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;

@Component
public class AdminController {

    @FXML
    private Button tourists;


    private final PrincipalController principalController;


    public AdminController(PrincipalController pController){this.principalController=pController;}



    @FXML
    void mostrarTuristas(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        if (event.getSource()==tourists){
            Parent root = fxmlLoader.load(TableOfTourists.class.getResourceAsStream("AddUser.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }




}
