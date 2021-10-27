package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import com.example.AppPrototipo.ui.PrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LikedController implements Initializable {

    private static Tourist tourist;

    @FXML
    private ImageView favoritosMarker;

    @FXML
    private GridPane grillaExperiencias;

    @FXML
    private ImageView perfilMarker;

    @FXML
    private ImageView sesionMarker;

    private final ExperienceRepository experienceRepository;

    private final MiniExperienceController miniExperienceController;

    public LikedController(ExperienceRepository experienceRepository, MiniExperienceController miniExperienceController) {
        this.experienceRepository = experienceRepository;
        this.miniExperienceController = miniExperienceController;
    }

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        LikedController.tourist = tourist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Experience> experiences = new ArrayList<>(liked(tourist));

        int columns = 0;
        int row = 1;

        try {
            for (int i=0; i < experiences.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
                fxmlLoader.setLocation(miniExperienceController.getClass().getResource("MiniExperience.fxml"));
                MiniExperienceController.setTourist(tourist);
                VBox vbox = fxmlLoader.load();
                miniExperienceController.setData(experiences.get(i));
                grillaExperiencias.add(vbox,columns++,row);
                GridPane.setMargin(vbox,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void perfilAction(ActionEvent event) throws IOException {
        //Perfil
    }

    public void favoritosAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        LikedController.setTourist(tourist);
        Parent root = fxmlLoader.load(LikedController.class.getResourceAsStream("LikedView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void cerrarSesionAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(PrincipalController.class.getResourceAsStream("Principal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private List<Experience> liked(Tourist tourist){
        return tourist.getLiked();
    }
}
