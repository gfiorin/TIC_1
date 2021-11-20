package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.ui.tourist.ExperienceController;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OperatorController implements Initializable {

    private static Operator operator;

    @FXML
    private JFXButton boton_1;

    @FXML
    private JFXButton boton_2;

    @FXML
    private JFXButton boton_3;

    @FXML
    private JFXButton boton_4;

    @FXML
    private AnchorPane innerView;

    @FXML
    private HBox topPane;

    private final ExperienceController experienceController;
    private final UserMgr userMgr;
    private final CreateExperienceController createExperienceController;
    private final ListOfExperiencesOpController listOfExperiencesOpController;
    private final AjustesOpController ajustesOpController;
    private final BookingsListOpController bookingsListOpController;

    public OperatorController(ExperienceController experienceController, UserMgr userMgr, CreateExperienceController createExperienceController, ListOfExperiencesOpController listOfExperiencesOpController, AjustesOpController ajustesOpController, BookingsListOpController bookingsListOpController) {
        this.experienceController = experienceController;
        this.userMgr = userMgr;
        this.createExperienceController = createExperienceController;
        this.listOfExperiencesOpController = listOfExperiencesOpController;
        this.ajustesOpController = ajustesOpController;
        this.bookingsListOpController = bookingsListOpController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showListOfExperiences();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createExperience() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(createExperienceController.getClass().getResource("CreateExperience.fxml"));
        AnchorPane createExperienceAP = fxmlLoader.load();
        loadToInnerView(createExperienceAP);
    }

    public void showListOfExperiences() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(listOfExperiencesOpController.getClass().getResource("ListOfExperiencesOp.fxml"));
        AnchorPane showListOfExperiencesAP = fxmlLoader.load();
        loadToInnerView(showListOfExperiencesAP);
    }

    public void ajustes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(ajustesOpController.getClass().getResource("AjustesOp.fxml"));
        AnchorPane showListOfExperiencesAP = fxmlLoader.load();
        loadToInnerView(showListOfExperiencesAP);
    }

    public void showBookingsList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(bookingsListOpController.getClass().getResource("BookingsListOp.fxml"));
        AnchorPane showListOfExperiencesAP = fxmlLoader.load();
        loadToInnerView(showListOfExperiencesAP);
    }

    @FXML
    void close(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void loadToInnerView(Node object){
        innerView.getChildren().setAll(object);
        AnchorPane.setBottomAnchor(object, 0.0);
        AnchorPane.setTopAnchor(object, 0.0);
        AnchorPane.setLeftAnchor(object, 0.0);
        AnchorPane.setRightAnchor(object, 0.0);
    }

}
