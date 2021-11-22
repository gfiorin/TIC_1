package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.TouristMgr;
import com.example.AppPrototipo.business.entities.Country;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.ui.admin.AdminController;
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
    private TableColumn<Tourist, List<Interest>> interests;

    @FXML
    private Button goBackBtn;

    private final TouristMgr touristMgr;

    public TouristsTableController(TouristMgr touristMgr) {
        this.touristMgr = touristMgr;
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
        interests.setCellValueFactory(new PropertyValueFactory<>("interests"));
    }

    private ObservableList<Tourist> getTourists() {
        List<Tourist> touristList = touristMgr.findAll();
        return FXCollections.observableArrayList(touristList);
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
