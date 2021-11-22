package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.*;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    private Button addImageBtn;

    @FXML
    private VBox typesOfExperiencesVBox;

    @FXML
    private Text imagesQuantity;

    private List<byte[]> images = new LinkedList<>();

    private FileChooser fileChooser = new FileChooser();

    private final ExperienceMgr experienceMgr;
    private final ExperienceTypeMgr experienceTypeMgr;
    private final DepartmentMgr departmentMgr;
    private final UserMgr userMgr;
    private final OperatorController operatorController;

    public CreateExperienceController(ExperienceMgr experienceMgr, ExperienceTypeMgr experienceTypeMgr, DepartmentMgr departmentMgr, UserMgr userMgr, @Lazy OperatorController operatorController) {
        this.experienceMgr = experienceMgr;
        this.experienceTypeMgr = experienceTypeMgr;
        this.departmentMgr = departmentMgr;
        this.userMgr = userMgr;
        this.operatorController = operatorController;
    }

    @FXML
    public void initialize(){

        imagesQuantity.setText("0");

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
    public void requestExperience(ActionEvent event) throws IOException {

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
                    "Uno o más campos están vacíos. Por favor, verifique la información introducida.");
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
                        showAlert("¡Información inválida!", "Por favor, ingrese una cantidad de personas válida.");
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
                        showAlert("¡Información inválida!", "Por favor, ingrese un precio válido.");
                        return;
                    }
                }

                boolean bookable = bookableInput.isSelected();
                Department department = departmentInput.getValue();
                String streetAndNo = streenAndNoInput.getText();
                String email = emailInput.getText();
                String link = linkInput.getText();
                String telephone = telephoneInput.getText();
                List<ExperienceType> experienceTypes = new ArrayList<>();

                for (Node node : typesOfExperiencesVBox.getChildren()) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        experienceTypes.add((ExperienceType) checkBox.getUserData());
                    }
                }

                TourOperator tourOperator = ((Operator) userMgr.getCurrentUser()).getTourOperator();

                if(images.size() < 1){
                    showAlert("¡Información inválida!", "La experiencia debe tener al menos una imagen asociada.");
                    return;
                }

                experienceMgr.addExperience(title, longDescription, shortDescription, vaccination, capacity, price, bookable, experienceTypes, tourOperator, department, streetAndNo, email, link, telephone, images);

                showAlert("Su experiencia ha sido solicitada con éxito", "Su experiencia ha sido solicitada con éxito, un administrador del sitio la revisará en los próximos días para habilitarla");

                images.clear();

                //lo mando a "mis experiencias"
                showListOfExperiences(event);

            } catch (InvalidInformation invalidInformation) {
                showAlert(
                        "¡Informacion inválida!",
                        invalidInformation.getMessage());
            }
        }
    }

    @FXML
    void agregarImagen(ActionEvent event){
        byte[] imagen;
        Scene sceneActual =((Node)event.getSource()).getScene();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        Path url = selectedFile.toPath();
        try {
            imagen = Files.readAllBytes(url);
            images.add(imagen);
            imagesQuantity.setText(String.valueOf(images.size()));
        }catch (IOException e){
            showAlert(
                    "¡Error!",
                    "Hubo un error al cargar la imagen");
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

    public void showListOfExperiences(ActionEvent actionEvent) throws IOException {
        Node source = (Node) actionEvent.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(operatorController.getClass().getResourceAsStream("OperatorMain.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
    }

}
