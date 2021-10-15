package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.ExperienceType;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.persistence.ExperienceTypeRepository;
import com.example.AppPrototipo.persistence.InterestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeOfExperienceController {


    private final ExperienceTypeRepository experienceTypeRepository;

    private final InterestRepository interestRepository;

    public TextField nameInput;

    public VBox interestVBox;

    public TypeOfExperienceController(ExperienceTypeRepository experienceTypeRepository, InterestRepository interestRepository) {
        this.experienceTypeRepository = experienceTypeRepository;
        this.interestRepository = interestRepository;
    }

    @FXML
    public void initialize(){
        List<Interest> interests = interestRepository.findAll();
        for(Interest interest : interests){
            CheckBox interestCheckBox = new CheckBox(interest.getName());
            interestCheckBox.setUserData(interest);
            interestVBox.getChildren().add(interestCheckBox);
        }

    }

    @FXML
    public void agregarExperienceType(ActionEvent event){
        String name = nameInput.getText();
        if (name == null){
            showAlert(
                    "Datos faltantes",
                    "Uno o mas campos esta vacio. Por favor, verifique la informacion introducida.");
        } else if (experienceTypeRepository.findOneByName(name) != null) {
            showAlert(
                    "Datos repetidos",
                    "Ya existe un tipo de experiencia con el nombre '" + name + "'. Por favor, verifique la informacion introducida.");
        } else {
            try {

                List<Interest> interests = new ArrayList<>();

                for (Node node : interestVBox.getChildren()) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        interests.add((Interest) checkBox.getUserData());
                    }
                }

                ExperienceType newType = new ExperienceType(name, interests);

                experienceTypeRepository.save(newType);

                close(event);


            } catch (Exception e) {}
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
