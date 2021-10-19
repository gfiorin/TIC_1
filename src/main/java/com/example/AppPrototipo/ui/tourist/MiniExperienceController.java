package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MiniExperienceController {

    private Experience experience;

    private Tourist tourist;

    @FXML
    private Text descripcionCorta;

    @FXML
    private ImageView experienciaImg;

    @FXML
    private Button likeBtn;

    @FXML
    private Text nombreExperiencia;

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
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
//        stage.show();
    }

    @FXML
    void likeAction(ActionEvent event) {
        Experience experience = getExperience();
        tourist.addLiked(experience);
    }
}
