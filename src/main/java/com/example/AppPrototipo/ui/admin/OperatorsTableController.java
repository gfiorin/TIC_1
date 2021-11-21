package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.OperatorMgr;
import com.example.AppPrototipo.business.entities.Operator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OperatorsTableController {

    private final OperatorMgr operatorMgr;

    @FXML
    private TableView<Operator> operatorTable;

    @FXML
    private TableColumn<Operator, Integer> id;

    @FXML
    private TableColumn<Operator, String> name;

    @FXML
    private TableColumn<Operator, String> username;

    @FXML
    private TableColumn<Operator, String> email;

    @FXML
    private TableColumn<Operator, TourOperator> tourOperator;

    @FXML
    private Button goBackBtn;

    public OperatorsTableController(OperatorMgr operatorMgr) {
        this.operatorMgr = operatorMgr;
    }

    @FXML
    public void initialize(){
        operatorTable.setItems(getOperators());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tourOperator.setCellValueFactory(new PropertyValueFactory<>("tourOperator"));
    }

    private ObservableList<Operator> getOperators() {
        List<Operator> operatorList = operatorMgr.findAll();
        return FXCollections.observableArrayList(operatorList);
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

}
