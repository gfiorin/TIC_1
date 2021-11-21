package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.ExperienceTypeMgr;
import com.example.AppPrototipo.business.managers.InterestMgr;
import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Interest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TypeOfExperienceController {

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<Interest> interestInput;

    @FXML
    private Button agregarBtn;

    @FXML
    private Button cancelarBtn;

    private final ExperienceTypeMgr experienceTypeMgr;

    private final InterestMgr interestMgr;

    public TypeOfExperienceController(ExperienceTypeMgr experienceTypeMgr, InterestMgr interestMgr) {
        this.experienceTypeMgr = experienceTypeMgr;
        this.interestMgr = interestMgr;
    }

    @FXML
    public void initialize(){
        List<Interest> interests = interestMgr.findAll();
        for(Interest interest : interests){
            interestInput.getItems().add(interest);
        }
    }

    @FXML
    public void agregarExperienceType(ActionEvent event){
        String name = nameInput.getText();
        Interest interest = interestInput.getValue();

        if (name.isEmpty() || interest == null) {
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else if (experienceTypeMgr.findOneByName(name) != null) {
            showAlert(
                    "Datos repetidos",
                    "Ya existe un tipo de experiencia con el nombre '" + name + "'. Por favor, verifique la informacion introducida.");
        } else {
            try {

                ExperienceType newType = new ExperienceType(name, interest);

                experienceTypeMgr.save(newType);

                showAlert("Tipo de experiencia creado con éxito","El tipo de experiencia fue creado con éxito");

                goBackToAdminView(event);

            } catch (Exception ignored) {} //todo: y esto pa que es?
        }

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
