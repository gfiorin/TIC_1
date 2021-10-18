package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.OperatorsRepository;
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
public class OperatorsTableController {

    private final OperatorsRepository operatorsRepository;

    @FXML
    private TableView<Operator> operatorTable;

    @FXML
    private TableColumn<Tourist, Integer> id;

    @FXML
    private TableColumn<Tourist, Integer> idTouristOperator;
    // modificar para que muestre el nombre del operador turistico en lugar del id


    public OperatorsTableController(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
    }

    @FXML
    public void initialize(){
        operatorTable.setItems(getOperators());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTouristOperator.setCellValueFactory(new PropertyValueFactory<>("idTouristOperator"));
    }


    private ObservableList<Operator> getOperators() {
        List<Operator> operatorList = operatorsRepository.findAll();
        return FXCollections.observableArrayList(operatorList);
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
