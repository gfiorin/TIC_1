package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.AdministratorMgr;
import com.example.AppPrototipo.business.entities.Administrator;
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
public class ListOfAdministratorsController {

    private final AdministratorMgr administratorMgr;

    @FXML
    private TableView<Administrator> adminTable;

    @FXML
    private TableColumn<Administrator, Integer> id;

    @FXML
    private TableColumn<Administrator, String> name;

    @FXML
    private TableColumn<Administrator, String> username;

    @FXML
    private TableColumn<Administrator, String> email;

    @FXML
    private Button goBackBtn;

    public ListOfAdministratorsController(AdministratorMgr administratorMgr) {
        this.administratorMgr = administratorMgr;
    }

    @FXML
    public void initialize(){
        adminTable.setItems(getAdministrators());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private ObservableList<Administrator> getAdministrators() {
        List<Administrator> administratorsList = administratorMgr.findAll();
        return FXCollections.observableArrayList(administratorsList);
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
