package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TouristOperatorsTableController {

    private final TourOperatorRepository tourOperatorRepository;

    @FXML
    private TableView<TourOperator> touristOperatorTable;

    @FXML
    private TableColumn<TourOperator, Integer> id;

    @FXML
    private TableColumn<TourOperator, String> companyName;

    @FXML
    private TableColumn<TourOperator, String> fantasyName;

    @FXML
    private TableColumn<TourOperator, String> webLink;

    @FXML
    private TableColumn<TourOperator, String> contactName;

    @FXML
    private TableColumn<TourOperator, String> contactPhone;

    @FXML
    private TableColumn<TourOperator, String> contactPosition;

    @FXML
    private TableColumn<TourOperator, String> contactEmail;

    @FXML
    private TableColumn<TourOperator, TinyIntTypeDescriptor> authorized;


    public TouristOperatorsTableController(TourOperatorRepository tourOperatorRepository) {
        this.tourOperatorRepository = tourOperatorRepository;
    }

    @FXML
    public void initialize(){

        touristOperatorTable.setItems(getTourOperators());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        fantasyName.setCellValueFactory(new PropertyValueFactory<>("fantasyName"));
        webLink.setCellValueFactory(new PropertyValueFactory<>("webLink"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactPhone.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
        contactPosition.setCellValueFactory(new PropertyValueFactory<>("contactPosition"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        authorized.setCellValueFactory(new PropertyValueFactory<>("authorized"));

    }

    private ObservableList<TourOperator> getTourOperators() {
        List<TourOperator> tourOperatorList = tourOperatorRepository.findAll();
        return FXCollections.observableArrayList(tourOperatorList);
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
