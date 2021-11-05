package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.ExperienceMgr;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class ExperienceController {

    @FXML
    private Button backBtn;

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

    private final ExperienceMgr experienceMgr;

    public ExperienceController(ExperienceMgr experienceMgr) {
        this.experienceMgr = experienceMgr;
    }

    @FXML
    void backAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("TouristMain.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void initialize(){
        Image imageMarker = new Image(getClass().getResourceAsStream("/imgs/location.png"));
        imageViewMarker.setImage(imageMarker);

        Experience experience = experienceMgr.findOneById(1);

        //Populacion de campos
        nombreExperiencia.setText(experience.getTitle());
        ubicacion.setText(experience.getUbicacion());
        vacunacion.setText((experience.isVaccination() ? "No" : "Si") + "requiere vacunacion");
//        email.setText(experience.);
//        nombreEmpresa.setText(experience.);
//        linkWeb.setText(experience.);
        descripcion.setText(experience.getDescription());
        descripcion.wrappingWidthProperty().bind(descripcionScrollPane.widthProperty());

        //Populacion de imagen
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
