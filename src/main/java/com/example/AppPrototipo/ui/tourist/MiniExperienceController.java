package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MiniExperienceController {

    private Experience experience;

    private static Tourist tourist;

    //private final UserMgr userMgr;

    @FXML
    private Text descripcionCorta;

    @FXML
    private ImageView experienciaImg;

    @FXML
    private Button likeBtn;

    @FXML
    private Text nombreExperiencia;

    /*public MiniExperienceController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }*/

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        MiniExperienceController.tourist = tourist;
    }

    public Experience getExperience() {
        return experience;
    }

    @FXML
    private Button verMasBtn;

    public void setData(Experience experience){
        this.experience = experience;
        nombreExperiencia.setText(experience.getTitle());
        descripcionCorta.setText(experience.getShortDescription());
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        experienciaImg.setImage(image);
    }

    @FXML
    void experienciaAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("ExperienceView.fxml"));
        ExperienceController ExperienceController = fxmlLoader.getController();
        ExperienceController.setExperience(getExperience());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void likeAction(ActionEvent event) {
        Experience experience = getExperience();
        for(Experience liked : tourist.getLiked()){
            if (liked.equals(experience)){
                showAlert(
                        "Atención",
                        "La experiencia ya se encuentra entre sus favoritas.");
            }
        }
        tourist.addLiked(experience);
        //userMgr.updateTourist(tourist);
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
