package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.managers.BookingMgr;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class MiniBookingController {

    private final BookingMgr bookingMgr;

    @FXML
    private Button cancelarBtn;

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

    @FXML
    private VBox infoVBox;

    public MiniBookingController(BookingMgr bookingMgr) {
        this.bookingMgr = bookingMgr;
    }

    public void setData(Booking booking){
        cancelarBtn.setUserData(booking.getId());
        String formatoFecha = "dd/MM/yyyy";
        DateFormat formatter = new SimpleDateFormat(formatoFecha);
        fechaReserva.setText(formatter.format(booking.getDate()));
        DateFormat format = new SimpleDateFormat("HH:mm");
        horaReserva.setText(format.format(booking.getTime()));
        nombreExperiencia.setText(booking.getExperience().getTitle());
        direccion.setText(booking.getExperience().getUbicacion());
        direccion.wrappingWidthProperty().bind(infoVBox.prefWidthProperty());
        cuposReserva.setText(String.valueOf(booking.getAmount()));
        if (!booking.getExperience().getImages().isEmpty()) {
            Image image = new Image(new ByteArrayInputStream(booking.getExperience().getImages().get(0).getImageData()));
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

    @FXML
    void cancelarOnAction(ActionEvent event) {
        Booking booking = bookingMgr.findById((Integer) cancelarBtn.getUserData());
        bookingMgr.delete(booking);
        cancelarBtn.setText("Cancelada");
        cancelarBtn.setStyle("-fx-background-color: green");
    }

}
