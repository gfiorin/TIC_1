package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class ExperienceController {

    private Experience experience;
    private int imageIndex = -1;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    private Text nombreExperiencia;
    @FXML
    private Text ubicacion;
    @FXML
    private Text vacunacion;
    @FXML
    private Text email;
    @FXML
    private Text nombreEmpresa;
    @FXML
    private Text telefono;
    @FXML
    private Text linkWeb;
    @FXML
    private Text descripcion;
    @FXML
    private ScrollPane descripcionScrollPane;

    @FXML
    private Pane imagePane;
    @FXML
    private ImageView imageViewPrincipal;
    @FXML
    private Text leftArrow;
    @FXML
    private Text rightArrow;

    private final ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @FXML
    private void initialize(){
        //SETUP INICIAL
        {
            leftArrow.layoutXProperty().bind(imagePane.layoutXProperty().add(14).subtract(8));
            leftArrow.layoutYProperty().bind(imagePane.layoutYProperty().add(imagePane.heightProperty().divide(2).add(86 / 4)));

            rightArrow.layoutXProperty().bind(imagePane.widthProperty().add(imagePane.layoutXProperty())
                    .subtract(14).subtract(8).subtract(rightArrow.getLayoutBounds().getWidth()));
            rightArrow.layoutYProperty().bind(imagePane.layoutYProperty().add(imagePane.heightProperty().divide(2)).add(86 / 4));
        }


        //POPOULACION
        this.experience = experienceRepository.findById(1);

        //Populacion de campos
        {
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
            telefono.setText(experience.getTelephone());
        }

        //Populacion de imagen principal
        nextImage(null);
    }

    @FXML
    private void nextImage(MouseEvent mouseEvent){
        imageIndex = (imageIndex + 1) % experience.getImages().size();
        setImage(imageIndex);
    }

    @FXML
    private void previousImage(MouseEvent mouseEvent){
        imageIndex = (imageIndex - 1) % experience.getImages().size();
        setImage(imageIndex);
    }

    private void setImage(int imageIndex) {
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(imageIndex).getImageData()));

        ChangeListener<Number> listenerNumber = getListenerNumber(imageViewPrincipal, image);
        ChangeListener<Image> listenerImage = getListener(imageViewPrincipal, image);

        imagePane.widthProperty().addListener(listenerNumber);
        imagePane.heightProperty().addListener(listenerNumber);
        imageViewPrincipal.imageProperty().addListener(listenerImage);

        imageViewPrincipal.setImage(image);
    }

    public ChangeListener<Number> getListenerNumber(ImageView imageViewPrincipal, Image image){
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

    public ChangeListener<Image> getListener(ImageView imageViewPrincipal, Image image) {
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

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
