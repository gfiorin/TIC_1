package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.*;
import com.example.AppPrototipo.business.managers.DepartmentMgr;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.ExperienceTypeMgr;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.util.List;

@Component
public class CreateExperienceController {

    private Operator operator;

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
    private TextArea shortDescription;

    @FXML
    private TextArea longDescription;

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

    public CreateExperienceController(ExperienceMgr experienceMgr, ExperienceTypeMgr experienceTypeMgr, DepartmentMgr departmentMgr) {
        this.experienceMgr = experienceMgr;
        this.experienceTypeMgr = experienceTypeMgr;
        this.departmentMgr = departmentMgr;
    }

    @FXML
    public void initialize(){
        shortDescription.setScrollLeft(0);
        longDescription.setScrollLeft(0);

        List<ExperienceType> experienceTypes = experienceTypeMgr.findAll();
        for(ExperienceType experienceType : experienceTypes){
            CheckBox experienceTypeCheckBox = new CheckBox(experienceType.getName());
            experienceTypeCheckBox.setUserData(experienceType);
            //typesOfExperiencesVBox.getChildren().add(experienceTypeCheckBox);
        }

        List<Department> departments = departmentMgr.findAll();
        for(Department department : departments){
            departmentInput.getItems().add(department);
        }

    }

    @FXML
    void requestExperience(ActionEvent event) {
    }

}
