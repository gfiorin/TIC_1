package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Country;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AdminController {

    @FXML
    private Button listOfTouristsBtn;

    @FXML
    private Button listOfOperatorsBtn;

    @FXML
    private Button listOfExperiencesBtn;

    @FXML
    private Button listOfTouristOperatorsBtn;

    @FXML
    private TableView<Tourist> touristTable;

    @FXML
    private TableColumn<Tourist, Integer> id;

    @FXML
    private TableColumn<Tourist, String> name;

    @FXML
    private TableColumn<Tourist, String> username;

    @FXML
    private TableColumn<Tourist, String> email;

    //@FXML
    //private TableColumn<Tourist, String> password;

    @FXML
    private TableColumn<Tourist, LocalDate> dateOfBirth;

    @FXML
    private TableColumn<Tourist, String> cellphone;

    @FXML
    private TableColumn<Tourist, String> documentType;

    @FXML
    private TableColumn<Tourist, String> documentNumber;

    @FXML
    private TableColumn<Tourist, Country> country;

    @FXML
    private TableColumn<Tourist, List<Interest>> listOfInterests;

    private final UserMgr userMgr;
    private final InterestRepository interestRepository;
    private final AdministratorRepository administratorRepository;
    private final OperatorsRepository operatorsRepository;
    private final TouristRepository touristRepository;
    private final TourOperatorRepository tourOperatorRepository;
    private final ExperienceRepository experienceRepository;
    private final ExperienceTypeRepository experienceTypeRepository;

    public AdminController(UserMgr userMgr,
                           InterestRepository interestRepository,
                           AdministratorRepository administratorRepository,
                           OperatorsRepository operatorsRepository,
                           TouristRepository touristRepository,
                           TourOperatorRepository tourOperatorRepository,
                           ExperienceRepository experienceRepository,
                           ExperienceTypeRepository experienceTypeRepository) {
        this.userMgr = userMgr;
        this.interestRepository = interestRepository;
        this.administratorRepository = administratorRepository;
        this.operatorsRepository = operatorsRepository;
        this.touristRepository = touristRepository;
        this.tourOperatorRepository = tourOperatorRepository;
        this.experienceRepository = experienceRepository;
        this.experienceTypeRepository = experienceTypeRepository;
    }

    @FXML
    void listadoDeTuristas(ActionEvent event) {

        List<Tourist> touristList = touristRepository.findAll();
        ObservableList<Tourist> touristObervableList = FXCollections.observableArrayList(touristList);

        id.setCellValueFactory(new PropertyValueFactory<Tourist,Integer>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Name"));
        username.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Username"));
        email.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Email"));
        //password.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Password"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Tourist,LocalDate>("Date of birth"));
        cellphone.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Cellphone"));
        documentType.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Document type"));
        documentNumber.setCellValueFactory(new PropertyValueFactory<Tourist,String>("Document number"));
        country.setCellValueFactory(new PropertyValueFactory<Tourist,Country>("Country"));
        listOfInterests.setCellValueFactory(new PropertyValueFactory<Tourist,List<Interest>>("Interests"));

        touristTable.setItems(touristObervableList);

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
