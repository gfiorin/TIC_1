
package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MiniExperienceController{

    private int idexperience;
    private final UserMgr userMgr;
    private final ExperienceMgr experienceMgr;
    private final TouristController touristController;

    @FXML
    private Text descripcionCorta;
    @FXML
    private ImageView experienciaImg;
    @FXML
    private ImageView heartImageView;
    @FXML
    private Text nombreExperiencia;
    @FXML
    private Button verMasBtn;
    @FXML
    private Pane imagePane;

    public MiniExperienceController(UserMgr userMgr, @Lazy TouristController touristController, ExperienceMgr experienceMgr) {
        this.userMgr = userMgr;
        this.touristController = touristController;
        this.experienceMgr = experienceMgr;
    }

    public int getIdExperience() {
        return idexperience;
    }

    public void setData(Experience experience, boolean likedByUser){
        verMasBtn.setUserData(experience.getId());
        nombreExperiencia.setText(experience.getTitle());
        descripcionCorta.setText(experience.getShortDescription());
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        ChangeListener<Number> listener = getListener(imagePane, experienciaImg, image);
        imagePane.widthProperty().addListener(listener);
        imagePane.heightProperty().addListener(listener);
        experienciaImg.setImage(image);

        //Heart
        heartImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            @Transactional
            public void handle(MouseEvent event) {
                Tourist tourist = userMgr.getCurrentTourist();
                Experience experience = experienceMgr.findById((Integer) verMasBtn.getUserData());
                if (!isLikedByUser(tourist, experience)){
                    tourist.getLiked().add(experience);
                    heartController(true);
                } else {
                    tourist.getLiked().removeIf(experienceToBeRemoved -> experienceToBeRemoved.getId().equals(experience.getId()));
                    heartController(false);
                }

                userMgr.updateTourist(tourist);
            }
        });

        heartController(likedByUser);
    }

    private boolean isLikedByUser(Tourist tourist, Experience experience) {
        return tourist.getLiked().stream().mapToInt(Experience::getId).anyMatch(id -> id == experience.getId());
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
        Experience experience = experienceMgr.findById((Integer) verMasBtn.getUserData());
        touristController.showExperience(experience);
    }

    private void heartController(boolean state){
        if(state){
            Image image = new Image("imgs/heart.png");
            heartImageView.setImage(image);
        } else {
            Image image = new Image("imgs/heart_outline.png");
            heartImageView.setImage(image);
        }
    }
}
