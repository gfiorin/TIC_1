package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Operator;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OperatorController implements Initializable {

    private static Operator operator;

    @FXML
    private JFXButton boton_1;

    @FXML
    private JFXButton boton_2;

    @FXML
    private JFXButton boton_3;

    @FXML
    private JFXButton boton_4;

    @FXML
    private ImageView favoritosMarker;

    @FXML
    private ImageView perfilMarker;

    @FXML
    private ImageView sesionMarker;

    @FXML
    private AnchorPane innerView;

    @FXML
    private VBox top;

    @FXML
    private VBox bottom;

    @FXML
    private VBox leftPane;

    public OperatorController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //bottom.prefHeightProperty().bind(leftPane.prefHeightProperty().subtract(top.prefHeightProperty()));
        //try {
       //     showListOfExperiences();
        //} catch (IOException e) {
        //    e.printStackTrace();
       // }
    }

    public void showListOfExperiences() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        //fxmlLoader.setLocation(ListOfExperincesOpController.getClass().getResource(".fxml"));
        ScrollPane experienceGrid = fxmlLoader.load();
        loadToInnerView(experienceGrid);
    }

    @FXML
    void close(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static Operator getOperator() {
        return operator;
    }

    public static void setOperator(Operator operator) {
        OperatorController.operator = operator;
    }

    private void loadToInnerView(Node object){
        innerView.getChildren().setAll(object);
        AnchorPane.setBottomAnchor(object, 0.0);
        AnchorPane.setTopAnchor(object, 0.0);
        AnchorPane.setLeftAnchor(object, 0.0);
        AnchorPane.setRightAnchor(object, 0.0);
    }

}
