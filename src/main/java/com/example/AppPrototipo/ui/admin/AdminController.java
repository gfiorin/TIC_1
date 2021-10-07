package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.persistence.*;
import com.example.AppPrototipo.ui.userCreation.UserCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AdminController {

    private final UserMgr userMgr;
    private final InterestRepository interestRepository;
    private final AdministratorRepository administratorRepository;
    private final OperatorsRepository operatorsRepository;
    private final TouristRepository touristRepository;
    private final TourOperatorRepository tourOperatorRepository;
    private final ExperienceRepository experienceRepository;
    private final ExperienceTypeRepository experienceTypeRepository;

    public AdminController(UserMgr userMgr,
                           InterestRepository interestRepository,
                           AdministratorRepository administratorRepository,
                           OperatorsRepository operatorsRepository,
                           TouristRepository touristRepository,
                           TourOperatorRepository tourOperatorRepository,
                           ExperienceRepository experienceRepository,
                           ExperienceTypeRepository experienceTypeRepository) {
        this.userMgr = userMgr;
        this.interestRepository = interestRepository;
        this.administratorRepository = administratorRepository;
        this.operatorsRepository = operatorsRepository;
        this.touristRepository = touristRepository;
        this.tourOperatorRepository = tourOperatorRepository;
        this.experienceRepository = experienceRepository;
        this.experienceTypeRepository = experienceTypeRepository;
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
