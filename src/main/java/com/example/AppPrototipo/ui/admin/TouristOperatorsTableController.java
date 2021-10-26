package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.TourOperatorMgr;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    @FXML
    private Button authorizeBtn;

    @FXML
    private Button goBackBtn;

    private final TourOperatorMgr tourOperatorMgr;

    public TouristOperatorsTableController(TourOperatorRepository tourOperatorRepository, TourOperatorMgr tourOperatorMgr) {
        this.tourOperatorRepository = tourOperatorRepository;
        this.tourOperatorMgr = tourOperatorMgr;
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
    private void enableOrDisableTO(ActionEvent event){
        TourOperator tourOperatorToModify = touristOperatorTable.getSelectionModel().getSelectedItem();
        tourOperatorMgr.changeAuthorizationOfTouristOperator(tourOperatorToModify.getId());
        touristOperatorTable.setItems(getTourOperators());
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
