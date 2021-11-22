package com.example.AppPrototipo.ui.admin.creation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.OperatorMgr;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AddOperatorController {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Button agregarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button selectBtn;

    @FXML
    private Label tourOperatorName;

    private final OperatorMgr operatorMgr;

    private TourOperator tourOperatorSelected = null;

    public AddOperatorController(OperatorMgr operatorMgr) {
        this.operatorMgr = operatorMgr;
    }

    @FXML
    public void addOperator(ActionEvent event) throws Exception {
        String name = nameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();

        if (name.isEmpty() || username.isEmpty() || password.length() < 6 || email.isEmpty() || tourOperatorSelected == null) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else {
            if (operatorMgr.findOneByEmail(email) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el email: " + email);
            } else if (operatorMgr.findOneByUsername(username) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el nombre de usuario: " + username);
            } else {

                Operator newOperator = new Operator(name, username, email, password, tourOperatorSelected);
                operatorMgr.save(newOperator);

                showAlert("Operador creado con exito","El operador a sido creado con éxito");

                goBackToAdminView(event);

            }
        }
    }

    @FXML
    public void init(TourOperator tourOperator) {
        tourOperatorSelected = tourOperator;
        tourOperatorName.setText(tourOperator.getCompanyName());
    }

    @FXML
    public void selectTourOperator(ActionEvent event) throws Exception {
        selectBtn.setDisable(true);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(TourOperatorSelectionTableController.class.getResourceAsStream("TourOperatorSelectTable.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.setOnCloseRequest(closeEvent -> {
            selectBtn.setDisable(false);
        });

        TourOperatorSelectionTableController touristOperatorsTableController = fxmlLoader.getController();

        newStage.showAndWait();

        tourOperatorSelected = touristOperatorsTableController.getSelectedItem(event);
        tourOperatorName.setText(tourOperatorSelected.getCompanyName());

        selectBtn.setDisable(false);
    }

    public void enableButton(){
        selectBtn.setDisable(false);
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
