package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.AdministratorMgr;
import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.business.managers.UserMgr;
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

import java.util.List;
import java.util.Optional;

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
    private Button deleteAdministratorBtn;

    @FXML
    private Button goBackBtn;

    private final UserMgr userMgr;

    public ListOfAdministratorsController(AdministratorMgr administratorMgr, UserMgr userMgr) {
        this.administratorMgr = administratorMgr;
        this.userMgr = userMgr;
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
    void deleteAdministrator(ActionEvent event) {
        boolean deleteAdministrator = deleteAdminstratorAlert("Confirmacion", "\n¿Esta seguro que desea eliminar a este administrador?");
        if (deleteAdministrator) {
            Administrator administratorToDelete = adminTable.getSelectionModel().getSelectedItem();
            if (administratorToDelete.equals(userMgr.getCurrentUser())){
                showAlert("Error!", "No puede eliminar un usuario administrador desde la misma sesión");
            }
            else {
                administratorMgr.deleteAdministratorById(administratorToDelete.getId());
                adminTable.setItems(getAdministrators());
            }
        }
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

    private boolean deleteAdminstratorAlert(String title, String contextText){

        ButtonType acceptBtn = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, contextText, acceptBtn, cancelBtn);
        alert.setTitle(title);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();

        return result.orElse(cancelBtn) == acceptBtn;

    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
