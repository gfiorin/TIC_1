package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class ExperienceController {

    @FXML
    AnchorPane rightAnchorPane;

    @FXML
    Text nombreExperiencia;
    @FXML
    Text ubicacion;
    @FXML
    Text vacunacion;
    @FXML
    Text email;
    @FXML
    Text nombreEmpresa;
    @FXML
    Text telefono;
    @FXML
    Text linkWeb;
    @FXML
    Text descripcion;
    @FXML
    ScrollPane descripcionScrollPane;

    @FXML
    Pane imagePane;
    @FXML
    ImageView imageViewPrincipal;
    @FXML
    ImageView imageViewMarker;

    private final ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @FXML
    private void initialize(){
        //POPOULACION
        Experience experience = experienceRepository.findById(1);

        //Populacion de campos
        nombreExperiencia.setText(experience.getTitle());
        nombreExperiencia.wrappingWidthProperty().bind(rightAnchorPane.prefWidthProperty());
        ubicacion.setText(experience.getUbicacion());
        ubicacion.wrappingWidthProperty().bind(rightAnchorPane.prefWidthProperty());

        vacunacion.setText((experience.isVaccination() ? "No" : "Si") + " requiere vacunacion");
        email.setText(experience.getEmail());
        nombreEmpresa.setText(experience.getTourOperator().getCompanyName());
        linkWeb.setText(experience.getLink());
        descripcion.setText(experience.getDescription());
        descripcion.wrappingWidthProperty().bind(descripcionScrollPane.widthProperty());

        //Populacion de imagenes
        Image imageMarker = new Image(getClass().getResourceAsStream("/imgs/location.png"));
        imageViewMarker.setImage(imageMarker);

        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        double oldImageWidth = image.getWidth(), oldImageHeight = image.getHeight();            //saving the original image size and ratio
        double imageRatio = oldImageWidth / oldImageHeight;

        imageViewPrincipal.setImage(image);

        ChangeListener<Number> listener = (obs, ov, nv) -> {
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

        imagePane.widthProperty().addListener(listener);
        imagePane.heightProperty().addListener(listener);
    }
}
