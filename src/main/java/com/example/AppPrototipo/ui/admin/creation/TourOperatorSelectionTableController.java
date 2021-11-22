package com.example.AppPrototipo.ui.admin.creation;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.TourOperatorMgr;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TourOperatorSelectionTableController {

    @FXML
    private TableView<TourOperator> touristOperatorTable;

    @FXML
    private TableColumn<TourOperator, Integer> id;

    @FXML
    private TableColumn<TourOperator, String> companyName;

    @FXML
    private TableColumn<TourOperator, String> fantasyName;

    @FXML
    private TableColumn<TourOperator, String> linkToWeb;

    @FXML
    private TableColumn<TourOperator, String> contactName;

    @FXML
    private TableColumn<TourOperator, String> contactPhone;

    @FXML
    private TableColumn<TourOperator, String> contactPosition;

    @FXML
    private TableColumn<TourOperator, String> contactEmail;

    @FXML
    private Button selectBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchInput;

    private final TourOperatorRepository tourOperatorRepository;

    private final TourOperatorMgr tourOperatorMgr;

    @Autowired
    public TourOperatorSelectionTableController(TourOperatorRepository tourOperatorRepository, TourOperatorMgr tourOperatorMgr) {
        this.tourOperatorRepository = tourOperatorRepository;
        this.tourOperatorMgr = tourOperatorMgr;
    }

    @FXML
    public void initialize(){
        touristOperatorTable.setItems(getTourOperators());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        fantasyName.setCellValueFactory(new PropertyValueFactory<>("fantasyName"));
        linkToWeb.setCellValueFactory(new PropertyValueFactory<>("linkToWeb"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactPhone.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
        contactPosition.setCellValueFactory(new PropertyValueFactory<>("contactPosition"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
    }

    private ObservableList<TourOperator> getTourOperators() {
        List<TourOperator> tourOperatorList = tourOperatorRepository.findAll();
        return FXCollections.observableArrayList(tourOperatorList);
    }

    @FXML
    private void searchItem(ActionEvent event) {
        touristOperatorTable.getItems().stream().filter(
                item -> Objects.equals(item.getCompanyName(), searchInput.getText()) ||
                        Objects.equals(item.getFantasyName(), searchInput.getText()) ||
                        Objects.equals(item.getLinkToWeb(), searchInput.getText()) ||
                        Objects.equals(item.getContactName(), searchInput.getText()) ||
                        Objects.equals(item.getContactEmail(), searchInput.getText())
        ).findAny().ifPresent(item -> {
            touristOperatorTable.getSelectionModel().select(item);
            touristOperatorTable.scrollTo(item);
        });
    }

    @FXML
    public TourOperator getSelectedItem(ActionEvent event) throws Exception {
        return touristOperatorTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void selection(ActionEvent event) {
        if (touristOperatorTable.getSelectionModel().getSelectedItem() == null){
            showAlert("Nada seleccionado", "Por favor, seleccione una fila de la tabla.");
        } else {
            close(event);
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
