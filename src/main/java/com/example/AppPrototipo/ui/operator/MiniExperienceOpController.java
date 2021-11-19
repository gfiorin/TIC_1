package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.context.annotation.Lazy;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MiniExperienceOpController {

    private int idexperience;
    private static Operator operator;
    private final UserMgr userMgr;
    private final ExperienceMgr experienceMgr;
    private final OperatorController operatorController;

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
    @FXML
    private Pane imagePane;

    public MiniExperienceOpController(UserMgr userMgr, @Lazy OperatorController operatorController, ExperienceMgr experienceMgr) {
        this.userMgr = userMgr;
        this.operatorController = operatorController;
        this.experienceMgr = experienceMgr;
    }

    public static Operator getOperator() {
        return operator;
    }

    public static void setOperator(Operator operator) {
        MiniExperienceOpController.operator=operator;
    }

    public int getIdExperience() {
        return idexperience;
    }

    public void setData(Experience experience){
        verMasBtn.setUserData(experience.getId());
        nombreExperiencia.setText(experience.getTitle());
        descripcionCorta.setText(experience.getShortDescription());
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        ChangeListener<Number> listener = getListener(imagePane, experienciaImg, image);
        imagePane.widthProperty().addListener(listener);
        imagePane.heightProperty().addListener(listener);
        experienciaImg.setImage(image);
    }

    private ChangeListener<Number> getListener(Pane imagePane, ImageView imageViewPrincipal, Image image) {
        double oldImageWidth = image.getWidth(), oldImageHeight = image.getHeight();            //saving the original image size and ratio
        double imageRatio = oldImageWidth / oldImageHeight;

        return (obs, ov, nv) -> {
            double paneWidth = imagePane.getWidth();
            double paneHeight = imagePane.getHeight();

            double paneRatio = paneWidth / paneHeight;                                          //calculating the new pane's ratio
            //after width or height changed
            double newImageWidth = oldImageWidth, newImageHeight = oldImageHeight;

            if (paneRatio > imageRatio) {
                newImageHeight = oldImageWidth / paneRatio;
            } else if (paneRatio < imageRatio) {
                newImageWidth = oldImageHeight * paneRatio;
            }

            imageViewPrincipal.setViewport(new Rectangle2D(                                     // The rectangle used to crop
                    (oldImageWidth - newImageWidth) / 2, (oldImageHeight - newImageHeight) / 2, //MinX and MinY to crop from the center
                    newImageWidth, newImageHeight)                                              // new width and height
            );

            imageViewPrincipal.setFitWidth(paneWidth);
        };
    }

    @FXML
    void experienciaAction(ActionEvent actionEvent) throws IOException {
//        Experience experience = experienceMgr.findById((Integer) verMasBtn.getUserData());
//        operatorController.showExperience(experience);

    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
