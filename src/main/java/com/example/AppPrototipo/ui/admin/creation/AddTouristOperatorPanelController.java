package com.example.AppPrototipo.ui.admin.creation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.TourOperatorMgr;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.TourOperatorAlreadyExists;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AddTouristOperatorPanelController {

    @FXML
    private TextField companyNameInput;

    @FXML
    private TextField fantasyNameInput;

    @FXML
    private TextField contactPhoneInput;

    @FXML
    private TextField contactEmailInput;

    @FXML
    private TextField contactNameInput;

    @FXML
    private TextField contactPositionInput;

    @FXML
    private TextField linkToWebInput;

    @FXML
    private CheckBox authorizedInput;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button agregarBtn;

    private final TourOperatorMgr tourOperatorMgr;

    public AddTouristOperatorPanelController(TourOperatorMgr tourOperatorMgr) {
        this.tourOperatorMgr = tourOperatorMgr;
    }

    @FXML
    void addTouristOperator(ActionEvent event) throws Exception{

        List<Control> controlList = Arrays.asList(companyNameInput, fantasyNameInput, contactPhoneInput, contactEmailInput, contactNameInput, contactPositionInput, linkToWebInput);

        boolean invalidValue = false;
        for (Control control : controlList) {
            if (control instanceof TextField) {
                if (((TextField) control).getText() == null || ((TextField) control).getText().isBlank()) {
                    invalidValue = true;
                    break;
                }
            }
        }

        if (invalidValue) {
            showAlert(
                    "Datos faltantes",
                    "Uno o más campos están vacíos. Por favor, verifique la información introducida.");
        }
        else {

            try {
                String companyName = companyNameInput.getText();
                String fantasyName = fantasyNameInput.getText();
                String contactPhone = contactPhoneInput.getText();
                String contactEmail = contactEmailInput.getText();
                String contactName = contactNameInput.getText();
                String contactPosition = contactPositionInput.getText();
                String linkToWeb = linkToWebInput.getText();
                boolean authorized = authorizedInput.isSelected();

                TourOperator tourOperatorAdded = tourOperatorMgr.addTourOperator(companyName, fantasyName, linkToWeb, contactName, contactPhone, contactPosition, contactEmail, authorized);

                boolean createOperator = touristOperatorCreatedAlert("Operador turístico creado con éxito", "¡El operador turístico ha sido creado con éxito!\n¿Desea asociar un operador a este operador turístico?");

                if (createOperator){

                    Node source = (Node) event.getSource();
                    Stage oldStage  = (Stage) source.getScene().getWindow();

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                    Parent root = fxmlLoader.load(AssociateOperatorToTOCreationController.class.getResourceAsStream("AssociateOperatorToTOCreation.fxml"));
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));

                    AssociateOperatorToTOCreationController associateOperatorToTOCreationController = fxmlLoader.getController();
                    associateOperatorToTOCreationController.setTourOperator(tourOperatorAdded);

                    newStage.show();

                    oldStage.close();
                }
                else {

                    goBackToAdminView(event);

                }

            } catch (TourOperatorAlreadyExists e) {
                showAlert(
                        "¡La compañía ya ha sido registrada en el sistema!",
                        e.getMessage());
            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "¡Informacion invalida!",
                        invalidInformation.getMessage());
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

    private boolean touristOperatorCreatedAlert(String title, String contextText){
        ButtonType acceptBtn = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, contextText, acceptBtn, cancelBtn);
        alert.setTitle(title);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();

        return result.orElse(cancelBtn) == acceptBtn;
    }

    @FXML
    void goBackToAdminView(ActionEvent event) throws Exception{
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

}
