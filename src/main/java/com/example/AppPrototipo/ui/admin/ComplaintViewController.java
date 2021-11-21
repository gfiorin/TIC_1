package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ComplaintViewController {

    @FXML
    private Button goBack;

    @FXML
    private Label email;

    @FXML
    private Label experience;

    @FXML
    private Label date;

    @FXML
    private Label description;

    private Complaint complaint;

    @FXML
    public void initialize(){
        if (complaint != null){
            email.setText(complaint.getTourist().getEmail());
            date.setText(complaint.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            experience.setText(complaint.getExperience().getTitle());
            description.setText(complaint.getDescription());
        }
    }

    public void setComplaint(Complaint complaint){
        this.complaint = complaint;
        initialize();
    }

    @FXML
    void goBackToComplaintTable(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ComplaintsListController.class.getResourceAsStream("ComplaintsList.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
    }

}
