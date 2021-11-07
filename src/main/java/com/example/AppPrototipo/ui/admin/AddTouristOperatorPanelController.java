package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.TourOperatorMgr;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.TourOperatorAlreadyExists;
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
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
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

                tourOperatorMgr.addTourOperator(companyName, fantasyName, linkToWeb, contactName, contactPhone, contactPosition, contactEmail, authorized);

                boolean createOperator = touristOperatorCreatedAlert("Operador turistico creado con exito", "El operador turistico ha sido creado con exito!\n¿Desea asociar un operador a este operador turistico?");

                if (createOperator){

                    Node source = (Node) event.getSource();
                    Stage oldStage  = (Stage) source.getScene().getWindow();

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                    Parent root = fxmlLoader.load(AdminController.class.getResourceAsStream("AddOperator.fxml"));
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));

                    AddOperatorController addOperatorController = fxmlLoader.getController();
                    addOperatorController.init(tourOperatorMgr.findOneByCompanyName(companyName));

                    newStage.showAndWait();
                    oldStage.close();

                }
                else {

                    close(event);

                }

            } catch (TourOperatorAlreadyExists e) {
                showAlert(
                        "La compañia ya ha sido registrada en el sistema!",
                        e.getMessage());
            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "Informacion invalida!",
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

}
