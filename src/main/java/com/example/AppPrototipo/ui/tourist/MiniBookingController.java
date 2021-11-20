package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MiniBookingController {
    @FXML
    private Text cuposReserva;

    @FXML
    private ImageView experienciaImg;

    @FXML
    private Text fechaReserva;

    @FXML
    private Text horaReserva;

    @FXML
    private Text nombreExperiencia;

    @FXML
    private Text direccion;

    @FXML
    private Pane imagePane;

    public void setData(Booking booking){
        String formatoFecha = "dd/MM/yyyy";
        DateFormat formatter = new SimpleDateFormat(formatoFecha);
        fechaReserva.setText(formatter.format(booking.getDate()));
        DateFormat format = new SimpleDateFormat("HH:mm");
        horaReserva.setText(format.format(booking.getTime()));
        nombreExperiencia.setText(booking.getExperience().getTitle());
        direccion.setText(booking.getExperience().getUbicacion());
        cuposReserva.setText(String.valueOf(booking.getAmount()));
        setImage(booking.getExperience(),0);
    }

    private void setImage(Experience experience, int imageIndex) {
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(imageIndex).getImageData()));

        ChangeListener<Number> listenerNumber = getListenerNumber(experienciaImg, image);
        ChangeListener<Image> listenerImage = getListener(experienciaImg, image);

        imagePane.widthProperty().addListener(listenerNumber);
        imagePane.heightProperty().addListener(listenerNumber);
        experienciaImg.imageProperty().addListener(listenerImage);

        experienciaImg.setImage(image);
    }

    private ChangeListener<Number> getListenerNumber(ImageView imageViewPrincipal, Image image){
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

    private ChangeListener<Image> getListener(ImageView imageViewPrincipal, Image image) {
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
