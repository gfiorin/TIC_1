package com.example.AppPrototipo.ui.user;

import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.InterestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class UserController {

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
    private VBox checkBoxes;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button agregarBtn;

    private final UserMgr userMgr;
    private final InterestRepository interestRepository;

    public UserController(UserMgr userMgr, InterestRepository interestRepository) {
        this.userMgr = userMgr;
        this.interestRepository = interestRepository;
    }

    @FXML
    public void initialize(){
        List<Interest> interests = interestRepository.findAll();
        for(Interest interest : interests){
            checkBoxes.getChildren().add(new CheckBox(interest.getName()));
        }
    }

    @FXML
    void agregarUsuario(ActionEvent event){

        List<Control> controlList = Arrays.asList(nameInput, emailInput, userInput, passwordInput, dateOfBirthInput, cellphoneInput);
        boolean invalidValue = false;
        for(Control control : controlList){
            if(control instanceof TextField){
                if (((TextField) control).getText() == null || ((TextField) control).getText().equals("")) {
                    invalidValue = true;
                    break;
                }
            } else if(control instanceof DatePicker){
                if(((DatePicker) control).getValue() == null){
                    invalidValue = true;
                    break;
                }
            }
        }

        if (invalidValue){
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else {

            try {

                String name = nameInput.getText();
                String email = emailInput.getText();
                String user = userInput.getText();
                String password = passwordInput.getText();
                LocalDate dateOfBirth = dateOfBirthInput.getValue();
                String cellphone = cellphoneInput.getText();


                userMgr.addTourist(name, user, email, password, dateOfBirth, cellphone, null, null);

                showAlert("Usuario creado con exito", "El usuario ha sido creado con exito");

                close(event);

            } catch (UserAlreadyExsists e) {
                showAlert(
                        "Email ya registrado",
                        "Ya existe un usuario registrado con ese email.");
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

}
