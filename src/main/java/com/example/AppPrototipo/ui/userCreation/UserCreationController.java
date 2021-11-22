package com.example.AppPrototipo.ui.userCreation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Country;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.CountryRepository;
import com.example.AppPrototipo.persistence.InterestRepository;
import com.example.AppPrototipo.ui.PrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserCreationController {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private DatePicker dateOfBirthInput;

    @FXML
    private TextField cellphoneInput;

    @FXML
    private ComboBox<Country> countryInput;

    @FXML
    private ComboBox<String> documentTypeInput;

    @FXML
    private TextField documentNumberInput;

    @FXML
    private VBox interestVBox;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button agregarBtn;

    private final UserMgr userMgr;
    private final InterestRepository interestRepository;
    private final CountryRepository countryRepository;

    public UserCreationController(UserMgr userMgr, InterestRepository interestRepository, CountryRepository countryRepository) {
        this.userMgr = userMgr;
        this.interestRepository = interestRepository;
        this.countryRepository = countryRepository;
    }

    @FXML
    public void initialize(){
        List<Interest> interests = interestRepository.findAll();
        for(Interest interest : interests){
            CheckBox interestCheckBox = new CheckBox(interest.getName());
            interestCheckBox.setUserData(interest);
            interestVBox.getChildren().add(interestCheckBox);
        }

        List<Country> countries = countryRepository.findAll();
        for(Country country : countries){
            countryInput.getItems().add(country);
        }

        List<String> documentTypes = Arrays.asList("CI", "DNI", "RG", "Pasaporte");
        for(String docType: documentTypes){
            documentTypeInput.getItems().add(docType);
        }

    }

    @FXML
    void agregarUsuario(ActionEvent event) throws IOException {

        //Control de errores
        List<Control> controlList = Arrays.asList(nameInput, emailInput, userInput, passwordInput, dateOfBirthInput, cellphoneInput, countryInput, documentTypeInput, documentNumberInput);
        boolean invalidValue = false;
        for (Control control : controlList) {
            if (control instanceof TextField) {
                if (((TextField) control).getText() == null || ((TextField) control).getText().isBlank()) {
                    invalidValue = true;
                    break;
                }
            } else if (control instanceof DatePicker) {
                if (((DatePicker) control).getValue() == null) {
                    invalidValue = true;
                    break;
                }
            } else if (control instanceof ComboBox) {
                if (((ComboBox) control).getValue() == null) {
                    invalidValue = true;
                    break;
                }
            }
        }

        boolean anyCheckboxChecked = false;
        for (Node node : interestVBox.getChildren()) {
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected()) {
                anyCheckboxChecked = true;
                break;
            }
        }

        if (invalidValue) {
            showAlert(
                    "Datos faltantes",
                    "Uno o más campos están vacíos. Por favor, verifique la información introducida.");
        } else if(!anyCheckboxChecked) {
            showAlert(
                    "Datos faltantes",
                    "Debe seleccionar al menos un interes. Por favor, verifique la información introducida.");
        } else {

            try {

                String name = nameInput.getText();
                String email = emailInput.getText();
                String user = userInput.getText();
                String password = passwordInput.getText();
                LocalDate dateOfBirth = dateOfBirthInput.getValue();
                String cellphone = cellphoneInput.getText();
                Country countryId = countryInput.getValue();
                String documentType = documentTypeInput.getValue();
                String documentNumber = documentNumberInput.getText();
                List<Interest> interests = new ArrayList<>();

                for (Node node : interestVBox.getChildren()) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        interests.add((Interest) checkBox.getUserData());
                    }
                }

                userMgr.addTourist(name, user, email, password, dateOfBirth, cellphone, countryId, interests, documentType, documentNumber);

                showAlert("Usuario creado con éxito", "El usuario ha sido creado con éxito");

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(PrincipalController.class.getResourceAsStream("Principal.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                close(event);

            } catch (UserAlreadyExsists e) {
                showAlert(
                        "¡Email o usuario ya existente!",
                        e.getMessage());
            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "¡Información inválida!",
                        invalidInformation.getMessage());
            }
        }
    }

    @FXML
    void goBackToMain(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(PrincipalController.class.getResourceAsStream("Principal.fxml"));
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
