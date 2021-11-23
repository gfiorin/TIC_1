package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.managers.BookingMgr;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class MiniExperienceOpController {

    private final ExperienceMgr experienceMgr;

    private final BookingMgr bookingMgr;

    @FXML
    private Text nombreExperiencia;

    @FXML
    private Text descripcion;

    @FXML
    private Text cantidadReservas;

    @FXML
    private Text estado;

    @FXML
    private ImageView experienciaImg;

    @FXML
    private Pane imagePane;

    public MiniExperienceOpController(ExperienceMgr experienceMgr, BookingMgr bookingMgr) {
        this.experienceMgr = experienceMgr;
        this.bookingMgr = bookingMgr;
    }

    public void setData(Experience experience){

        experience = experienceMgr.getCurrentExperience(experience.getId());

        String e;
        if (experience.isAuthorized()) e = "Habilitada";
        else e = "Deshabilitada";

        nombreExperiencia.setText(experience.getTitle());
        cantidadReservas.setText(String.valueOf(bookingMgr.findByExperience(experience).size()));
        descripcion.setText(String.valueOf(experience.getShortDescription()));
        estado.setText(e);
        if (!experience.getImages().isEmpty()){
            Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
            ChangeListener<Number> listener = getListener(imagePane, experienciaImg, image);
            imagePane.widthProperty().addListener(listener);
            imagePane.heightProperty().addListener(listener);
            experienciaImg.setImage(image);
        }
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


}
