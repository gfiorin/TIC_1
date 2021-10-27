package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MiniExperienceController implements Initializable {

    private Experience experience;
    private static Tourist tourist;

    private final UserMgr userMgr;
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

    public MiniExperienceController(UserMgr userMgr,@Lazy TouristController touristController) {
        this.userMgr = userMgr;
        this.touristController = touristController;
    }

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        MiniExperienceController.tourist = tourist;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setData(Experience experience){
        this.experience = experience;
        nombreExperiencia.setText(experience.getTitle());
        descripcionCorta.setText(experience.getShortDescription());
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        experienciaImg.setImage(image);
    }

    @FXML
    void experienciaAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
//        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("ExperienceView.fxml"));
//        ExperienceController ExperienceController = fxmlLoader.getController();
//        ExperienceController.setExperience(getExperience());
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);
//        fxmlLoader.load(TouristController.class.getResourceAsStream("TouristMain.fxml"));
//        TouristController touristController = fxmlLoader.getController();
        touristController.showExperience(experience);
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
        userMgr.updateTourist(tourist);
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
