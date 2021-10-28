package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class MiniExperienceController {

    private int idexperience;
    private static Tourist tourist;
    private final UserMgr userMgr;
    private final ExperienceMgr experienceMgr;
    private final TouristController touristController;

    @FXML
    private Text descripcionCorta;
    @FXML
    private ImageView experienciaImg;
    @FXML
    private Button likeBtn;
    @FXML
    private Text nombreExperiencia;
    @FXML
    private Button verMasBtn;

    public MiniExperienceController(UserMgr userMgr, @Lazy TouristController touristController, ExperienceMgr experienceMgr) {
        this.userMgr = userMgr;
        this.touristController = touristController;
        this.experienceMgr = experienceMgr;
    }

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        MiniExperienceController.tourist = tourist;
    }

    public int getIdExperience() {
        return idexperience;
    }

    public void setData(Experience experience){
        verMasBtn.setUserData(experience.getId());
        nombreExperiencia.setText(experience.getTitle());
        descripcionCorta.setText(experience.getShortDescription());
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        experienciaImg.setImage(image);
    }

    @FXML
    void experienciaAction(ActionEvent actionEvent) throws IOException {
        Experience experience = experienceMgr.findById((Integer) verMasBtn.getUserData());
        touristController.showExperience(experience);
    }

    @FXML
    void likeAction(ActionEvent event) {
        Experience experience = experienceMgr.findById((Integer) verMasBtn.getUserData());
        boolean yaFavorita = false;
        for(Experience liked : tourist.getLiked()){
            if (liked.getTitle().equals(experience.getTitle())){
                showAlert(
                        "Atención",
                        "La experiencia ya se encuentra entre sus favoritas.");
                yaFavorita = true;
            }
        }
        if(!yaFavorita){
        tourist.addLiked(experience);
        userMgr.updateTourist(tourist);
        }
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
