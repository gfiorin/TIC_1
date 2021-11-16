package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MiniBookingController {
    @FXML
    private Text cuposReserva;

    @FXML
    private Text fechaReserva;

    @FXML
    private Text horaReserva;

    @FXML
    private Text nombreExperiencia;

    @FXML
    private Text direccion;

    public void setData(Booking booking){
        String formatoFecha = "dd/MM/yyyy";
        DateFormat formatter = new SimpleDateFormat(formatoFecha);
        fechaReserva.setText(formatter.format(booking.getDate()));
        DateFormat format = new SimpleDateFormat("HH:mm");
        horaReserva.setText(format.format(booking.getTime()));
        nombreExperiencia.setText(booking.getExperience().getTitle());
        direccion.setText(booking.getExperience().getUbicacion());
        cuposReserva.setText(String.valueOf(booking.getAmount()));
    }

}
