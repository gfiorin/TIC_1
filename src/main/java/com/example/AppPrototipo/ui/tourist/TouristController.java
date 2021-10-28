package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.ui.PrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TouristController implements Initializable {

    private static Tourist tourist;

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

    private final ExperienceController experienceController;
    private final ExperienceGridController experienceGridController;
    private final LikedController likedController;

    public TouristController(ExperienceController experienceController, ExperienceGridController experienceGridController, LikedController likedController) {
        this.experienceController = experienceController;
        this.experienceGridController = experienceGridController;
        this.likedController = likedController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bottom.prefHeightProperty().bind(leftPane.prefHeightProperty().subtract(top.prefHeightProperty()));

        try {
            showExperienceGrid();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        TouristController.tourist = tourist;
    }

    public void showProfile(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        ProfileController.setTourist(tourist);
        Parent root = fxmlLoader.load(ProfileController.class.getResourceAsStream("ProfileView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public  void showFavourites(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(likedController.getClass().getResource("LikedView.fxml"));
        likedController.setTourist(tourist);
        ScrollPane experiencia = fxmlLoader.load();
        loadToInnerView(experiencia);
    }

    public void cerrarSesionAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(PrincipalController.class.getResourceAsStream("Principal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void showExperience(Experience experience) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(experienceController.getClass().getResource("ExperienceView.fxml"));
        experienceController.setExperience(experience);
        BorderPane experiencia = fxmlLoader.load();
        loadToInnerView(experiencia);
    }

    public void showExperienceGrid() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        fxmlLoader.setLocation(experienceGridController.getClass().getResource("ExperienceGrid.fxml"));
        experienceGridController.setTourist(tourist);
        ScrollPane experienceGrid = fxmlLoader.load();
        loadToInnerView(experienceGrid);
    }

    private void loadToInnerView(Node object){
        innerView.getChildren().setAll(object);
        AnchorPane.setBottomAnchor(object, 0.0);
        AnchorPane.setTopAnchor(object, 0.0);
        AnchorPane.setLeftAnchor(object, 0.0);
        AnchorPane.setRightAnchor(object, 0.0);
    }

}
