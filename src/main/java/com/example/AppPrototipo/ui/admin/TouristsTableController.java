package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.Country;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.TouristRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TouristsTableController {

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

    private final TouristRepository touristRepository;

    public TouristsTableController(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @FXML
    public void initialize(){

        touristTable.setItems(getTourists());
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        cellphone.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        documentType.setCellValueFactory(new PropertyValueFactory<>("documentType"));
        documentNumber.setCellValueFactory(new PropertyValueFactory<>("documentNumber"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        //listOfInterests.setCellValueFactory(new PropertyValueFactory<>("Interests"));

    }

    private ObservableList<Tourist> getTourists() {
        List<Tourist> touristList = touristRepository.findAll();
        return FXCollections.observableArrayList(touristList);
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
