package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.managers.ComplaintMgr;
import com.example.AppPrototipo.business.entities.Complaint;
import com.example.AppPrototipo.business.entities.Experience;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ComplaintsListController {

    private final ComplaintMgr complaintMgr;

    @FXML
    private TableView<Complaint> complaintsTable;

    @FXML
    private TableColumn<Complaint, Integer> id;

    @FXML
    private TableColumn<Complaint, String> description;

    @FXML
    private TableColumn<Complaint, LocalDateTime> date;

    @FXML
    private TableColumn<Complaint, Experience> experience;

    @FXML
    private TableColumn<Complaint, Tourist> tourist;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button checkComplaintBtn;

    public ComplaintsListController(ComplaintMgr complaintMgr) {
        this.complaintMgr = complaintMgr;
    }

    @FXML
    public void initialize(){
        complaintsTable.setItems(getComplaints());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        tourist.setCellValueFactory(new PropertyValueFactory<>("tourist"));
    }

    private ObservableList<Complaint> getComplaints() {
        List<Complaint> complaintsList = complaintMgr.findAll();
        return FXCollections.observableArrayList(complaintsList);
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
    public void selectComplaint(ActionEvent event) throws Exception{
        Complaint complaint = complaintsTable.getSelectionModel().getSelectedItem();

        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ComplaintViewController.class.getResourceAsStream("ComplaintView.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        ComplaintViewController complaintViewController = fxmlLoader.getController();

        newStage.show();

        complaintViewController.setComplaint(complaint);

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
