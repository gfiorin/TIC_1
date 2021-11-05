package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
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
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ExperiencesTableController {

    @FXML
    private TableView<Experience> experiencesTable;

    @FXML
    private TableColumn<Experience, Integer> id;

    @FXML
    private TableColumn<Experience, String> title;

    @FXML
    private TableColumn<Experience, String> department;

    @FXML
    private TableColumn<Experience, Boolean> authorized;

    @FXML
    private TableColumn<Experience, Float> price;

    @FXML
    private TableColumn<Experience, String> description;

    @FXML
    private TableColumn<Experience, Boolean> vaccination;

    @FXML
    private TableColumn<Experience, Integer> capacity;

    @FXML
    private TableColumn<Experience, Boolean> bookable;

    @FXML
    private TableColumn<Experience, Boolean> reviewed;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button authorizeBtn;

    private final ExperienceMgr experienceMgr;

    public ExperiencesTableController(ExperienceMgr experienceMgr) {
        this.experienceMgr = experienceMgr;
    }

    @FXML
    public void initialize(){
        experiencesTable.setItems(getExperiences());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        authorized.setCellValueFactory(new PropertyValueFactory<>("authorized"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        vaccination.setCellValueFactory(new PropertyValueFactory<>("vaccination"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        bookable.setCellValueFactory(new PropertyValueFactory<>("bookable"));
        reviewed.setCellValueFactory(new PropertyValueFactory<>("reviewed"));
    }

    private ObservableList<Experience> getExperiences() {
        List<Experience> experienceList = experienceMgr.findAll();
        return FXCollections.observableArrayList(experienceList);
    }

    @FXML
    private void enableOrDisableExperience(ActionEvent event){
        Experience experienceToModify = experiencesTable.getSelectionModel().getSelectedItem();
        experienceMgr.changeAuthorizationOfExperience(experienceToModify.getId());
        experiencesTable.setItems(getExperiences());
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
