package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.OperatorsRepository;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.AnnotationVisitorTee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class AddOperatorController {

    private final OperatorsRepository operatorsRepository;


    public AddOperatorController(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
    }

    public TextField nameInput;
    public TextField usernameInput;
    public TextField passwordInput;
    public TextField emailInput;

    public Button agregarBtn;
    public Button cancelarBtn;
    public Button selectBtn;

    @FXML
    private Label tourOperatorName;

    private TourOperator tourOperatorSelected = null;

    public Label getTourOperatorName() {
        return tourOperatorName;
    }

    public TourOperator getTourOperatorSelected() {
        return tourOperatorSelected;
    }

    public void setTourOperatorSelected(TourOperator tourOperatorSelected) {
        this.tourOperatorSelected = tourOperatorSelected;
    }

    @FXML
    public void addOperator(ActionEvent event) {
        String name = nameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();

        if (name.isEmpty() || username.isEmpty() || password.length() < 6 || email.isEmpty() || tourOperatorSelected == null) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else {
            if (operatorsRepository.findOneByEmail(email) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el email: " + email);
            } else if (operatorsRepository.findOneByUsername(username) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el nombre de usuario: " + username);
            } else {
                try {

                    Operator newOperator = new Operator(name, username, email, password, tourOperatorSelected);
                    operatorsRepository.save(newOperator);

                    showAlert("Operador creado con exito","El operador a sido creado con éxito");

                    close(event);
                    
                } catch (Exception ignored) {}
            }

        }
    }

    @FXML
    public void selectTourOperator(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AdminController.class.getResourceAsStream("TourOperatorSelectTable.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        TourOperatorSelectionTableController touristOperatorsTableController = fxmlLoader.getController();

        newStage.showAndWait();

        tourOperatorSelected = touristOperatorsTableController.getSelectedItem(event);
        tourOperatorName.setText(tourOperatorSelected.getCompanyName());

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
