package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Tourist;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Map;

@Component
public class ExperienceController {

    private final ExperienceMgr experienceMgr;
    private final UserMgr userMgr;
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

    @FXML
    private DatePicker calendarioReserva;
    @FXML
    private ComboBox<TimeWrapper> horarioReserva;
    @FXML
    private ComboBox<Integer> cuposAReservar;
    @FXML
    private Button reservarBtn;
    @FXML
    private Text mensajeConfirmacion;

    public ExperienceController(ExperienceMgr experienceMgr, UserMgr userMgr) {
        this.experienceMgr = experienceMgr;
        this.userMgr = userMgr;
    }

    @FXML
    private void initialize(){
        //SETUP INICIAL
        responsiveSetup();
        calendarioSetup();
        horarioReset();
        mensajeConfirmacion.setVisible(false);

        //POPOULACION
        populacionDeCampos();
        //Populacion de imagen principal
        nextImage(null);

    }

    private void populacionDeCampos() {
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

    private void calendarioSetup() {
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);

                        if(date.compareTo(LocalDate.now().plusDays(30)) > 0 ||
                                date.compareTo(LocalDate.now()) < 0 ||
                                !experienceMgr.isRemainingCapacityForExperienceOnDate(experience, Date.valueOf(date))){
                            setDisable(true);
                        } else {
                            setStyle("-fx-background-color: #aaea97;");
                        }
                    }
                };
            }
        };

        calendarioReserva.setDayCellFactory(dayCellFactory);
    }

    private void responsiveSetup() {
        leftArrow.layoutXProperty().bind(imagePane.layoutXProperty().add(14).subtract(8));
        leftArrow.layoutYProperty().bind(imagePane.layoutYProperty().add(imagePane.heightProperty().divide(2).add(86 / 4)));

        rightArrow.layoutXProperty().bind(imagePane.widthProperty().add(imagePane.layoutXProperty())
                .subtract(14).subtract(8).subtract(rightArrow.getLayoutBounds().getWidth()));
        rightArrow.layoutYProperty().bind(imagePane.layoutYProperty().add(imagePane.heightProperty().divide(2)).add(86 / 4));
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

    public void setExperience(Experience experience) {
        this.experience = experience;
        imageIndex = -1;
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

    @FXML
    private void calendarioOnAction(ActionEvent event){
        LocalDate datePicked = calendarioReserva.getValue();
        horarioReset();
        calendarioReserva.setStyle(null);

        if(datePicked != null){

            Map<Time, Integer> timeMap = experienceMgr.remainingCapacityForExperienceOnDate(experience, Date.valueOf(datePicked));

            if(datePicked.compareTo(LocalDate.now().plusDays(30)) > 0 ||
                    datePicked.compareTo(LocalDate.now()) < 0 ||
                    timeMap.values().stream().mapToInt(Integer::intValue).sum() <= 0) {

                calendarioReserva.setStyle("-fx-background-color: #d74b4b");
            } else {
                calendarioReserva.setStyle(null);
                horarioInitalize(timeMap);
            }

        }
    }

    private void horarioInitalize(Map<Time, Integer> timeMap){

        horarioReserva.getItems().clear();

        timeMap.forEach(((time, remainingCapacity) -> {
            if(remainingCapacity > 0) {
                horarioReserva.getItems().add(new TimeWrapper(time, remainingCapacity));
            }
        }));
        horarioReserva.setDisable(false);
    }

    private void horarioReset(){
        horarioReserva.setDisable(true);
        horarioReserva.setValue(null);
        cuposAReservarReset();
    }

    @FXML
    private void horarioOnAction() {
        if (horarioReserva.getValue() != null) {
            cuposAReservarInitalize(horarioReserva.getValue().remainingCapacity);
        } else {
            cuposAReservarReset();
        }
    }

    private void cuposAReservarInitalize(int cuposDisponibles){

        cuposAReservar.getItems().clear();

        for (int i = 1; i <= cuposDisponibles; i++) {
            cuposAReservar.getItems().add(i);
        }
        cuposAReservar.setDisable(false);
    }

    private void cuposAReservarReset(){
        cuposAReservar.setDisable(true);
        cuposAReservar.setValue(null);
        reservarBtn.setDisable(true);
    }

    @FXML
    private void cuposAReservarOnAction(ActionEvent actionEvent){
        reservarBtn.setDisable(cuposAReservar.getValue() == null);
    }

    @FXML
    private void reservarBtnOnAction(){
        experienceMgr.createNewReservation(
                experience,
                (Tourist) userMgr.getCurrentUser(),
                Date.valueOf(calendarioReserva.getValue()),
                horarioReserva.getValue().time,
                cuposAReservar.getValue()
        ); //Se hace la reserva

        //Se procede a deshabilitar todo
        calendarioReserva.setDisable(true);
        horarioReserva.setDisable(true);
        cuposAReservar.setDisable(true);
        reservarBtn.setDisable(true);

        //Se muestra el mensaje de confirmacion
        mensajeConfirmacion.setVisible(true);
    }

    private class TimeWrapper {

        public Time time;
        public int remainingCapacity;

        public TimeWrapper(Time time, int remainingCapacity) {
            this.time = time;
            this.remainingCapacity = remainingCapacity;
        }

        public Time getTime() {
            return time;
        }

        @Override
        public String toString() {
            return time.toString();
        }
    }
}
