package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.*;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.managers.DepartmentMgr;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.ExperienceTypeMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CreateExperienceController {

    @FXML
    private TextField titleInput;

    @FXML
    private TextField telephoneInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField streenAndNoInput;

    @FXML
    private TextField linkInput;

    @FXML
    private TextField capacityInput;

    @FXML
    private TextArea shortDescriptionInput;

    @FXML
    private TextArea longDescriptionInput;

    @FXML
    private ComboBox<Department> departmentInput;

    @FXML
    private CheckBox vaccinationInput;

    @FXML
    private CheckBox doesNotHavePriceInput;

    @FXML
    private CheckBox doesNotHaveMaxCapacityInput;

    @FXML
    private CheckBox bookableInput;

    @FXML
    private Button createExperienceBtn;

    @FXML
    private VBox typesOfExperiencesVBox;

    private final ExperienceMgr experienceMgr;
    private final ExperienceTypeMgr experienceTypeMgr;
    private final DepartmentMgr departmentMgr;
    private final UserMgr userMgr;

    public CreateExperienceController(ExperienceMgr experienceMgr, ExperienceTypeMgr experienceTypeMgr, DepartmentMgr departmentMgr, UserMgr userMgr) {
        this.experienceMgr = experienceMgr;
        this.experienceTypeMgr = experienceTypeMgr;
        this.departmentMgr = departmentMgr;
        this.userMgr = userMgr;
    }

    @FXML
    public void initialize(){

        List<ExperienceType> experienceTypes = experienceTypeMgr.findAll();
        for(ExperienceType experienceType : experienceTypes){
            CheckBox experienceTypeCheckBox = new CheckBox(experienceType.getName());
            experienceTypeCheckBox.setUserData(experienceType);
            typesOfExperiencesVBox.getChildren().add(experienceTypeCheckBox);
        }

        List<Department> departments = departmentMgr.findAll();
        for(Department department : departments){
            departmentInput.getItems().add(department);
        }

    }

    @FXML
    public void requestExperience(ActionEvent event) {

        //Control de errores
        List<Control> controlList = Arrays.asList(titleInput, telephoneInput, emailInput, priceInput, streenAndNoInput, linkInput, capacityInput, shortDescriptionInput, longDescriptionInput, departmentInput);
        boolean invalidValue = false;
        for (Control control : controlList) {
            if (control instanceof TextField) {
                if (((TextField) control).getText() == null || ((TextField) control).getText().isBlank()) {
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
        for (Node node : typesOfExperiencesVBox.getChildren()) {
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected()) {
                anyCheckboxChecked = true;
                break;
            }
        }

        if (invalidValue) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la información introducida.");
        } else if(!anyCheckboxChecked) {
            showAlert(
                    "Datos faltantes",
                    "Debe seleccionar al menos un tipo de experiencia asociada. Por favor, verifique la información introducida.");
        } else {

            try {

                String title = titleInput.getText();
                String longDescription = longDescriptionInput.getText();
                String shortDescription = shortDescriptionInput.getText();
                boolean vaccination = vaccinationInput.isSelected();

                boolean doesNotHaveCapacity = doesNotHaveMaxCapacityInput.isSelected();
                Integer capacity = 0;
                if(!doesNotHaveCapacity) {
                    try {
                        capacity = Integer.parseInt(capacityInput.getText());
                    } catch (NumberFormatException e) {
                        showAlert("Informacion invalida!", "Por favor ingrese una cantidad de personas válida");
                        return;
                    }
                }

                boolean doesNotHavePrice = doesNotHavePriceInput.isSelected();
                BigDecimal price = BigDecimal.valueOf(0);
                if(!doesNotHavePrice){
                    try {
                        price = BigDecimal.valueOf(Double.parseDouble(priceInput.getText()));
                    }
                    catch (NumberFormatException e){
                        showAlert("Informacion invalida!", "Por favor ingrese un precio válido");
                        return;
                    }
                }

                boolean bookable = bookableInput.isSelected();
                Department department = departmentInput.getValue();
                String streetAndNo = streenAndNoInput.getText();
                String email = emailInput.getText();
                String link = linkInput.getText();
                String telephone = emailInput.getText();
                List<ExperienceType> experienceTypes = new ArrayList<>();

                for (Node node : typesOfExperiencesVBox.getChildren()) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        experienceTypes.add((ExperienceType) checkBox.getUserData());
                    }
                }

                List<Image> images; //todo

                TourOperator tourOperator = ((Operator) userMgr.getCurrentUser()).getTourOperator();

                //experienceMgr.addExperience(title, longDescription, shortDescription, vaccination, capacity, price, bookable, experienceTypes, tourOperator, images, department, streetAndNo, email, link, telephone);
                if(bookable) {
                    throw new InvalidInformation("e");
                }

                showAlert("Su experiencia ha sido solicitada con exito", "Su experiencia ha sido solicitada con exito, un administrador del sitio");

                initialize();//todo

            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "Informacion invalida!",
                        invalidInformation.getMessage());
            }
        }
    }

    @FXML
    void close(javafx.event.ActionEvent event){
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
