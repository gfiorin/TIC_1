package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.OperatorsRepository;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class AddOperatorController {

    private final OperatorsRepository operatorsRepository;
    private final TourOperatorRepository tourOperatorRepository;

    public AddOperatorController(OperatorsRepository operatorsRepository, TourOperatorRepository tourOperatorRepository) {
        this.operatorsRepository = operatorsRepository;
        this.tourOperatorRepository = tourOperatorRepository;
    }

    public TextField nameInput;
    public TextField usernameInput;
    public TextField passwordInput;
    public TextField emailInput;
    public TextField tourOperatorNameInput;

    public Button agregarBtn;
    public Button cancelarBtn;

    @FXML
    public void addOperator(ActionEvent event) {
        String name = nameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();
        String tourOperatorName = tourOperatorNameInput.getText();

        if (name.isEmpty() || username.isEmpty() || password.length() < 6 || email.isEmpty() || tourOperatorName.isEmpty()) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else {
            TourOperator tourOperator = tourOperatorRepository.findOneByCompanyName(tourOperatorName);
            if (tourOperator == null) {
                showAlert(
                        "Operador turistico inexistente",
                        "No existe un operador turistico con el nombre: " + tourOperatorName);
            } else if (operatorsRepository.findOneByEmail(email) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el email: " + email);
            } else if (operatorsRepository.findOneByUsername(username) != null) {
                showAlert(
                        "Operador ya existe",
                        "Ya existe un operador con el nombre de usuario: " + username);
            } else {
                try {

                    Operator newOperator = new Operator(name, username, email, password, tourOperator);
                    operatorsRepository.save(newOperator);

                    showAlert("Tipo de experiencia creado con éxito","El tipo de experiencia fue creado con éxito");

                    close(event);
                    
                } catch (Exception ignored) {}
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
}
