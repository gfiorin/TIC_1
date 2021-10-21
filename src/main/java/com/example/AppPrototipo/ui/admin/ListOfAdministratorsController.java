package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.persistence.AdministratorRepository;
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
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ListOfAdministratorsController {

    private final AdministratorRepository administratorRepository;


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



    public ListOfAdministratorsController(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
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
        List<Administrator> administratorsList = administratorRepository.findAll();
        return FXCollections.observableArrayList(administratorsList);
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
