package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.BookingMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookingsController implements Initializable {

    private final UserMgr userMgr;
    private final BookingMgr bookingMgr;

    @FXML
    private Text titulo;

    @FXML
    private VBox vBoxGral;

    @FXML
    private VBox vBoxReservas;

    public BookingsController(UserMgr userMgr, BookingMgr bookingMgr) {
        this.userMgr = userMgr;
        this.bookingMgr = bookingMgr;
    }

    @Bean
    @Scope("prototype")
    private MiniBookingController miniBookingControllerPrototype() {
        return new MiniBookingController(bookingMgr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tourist tourist = userMgr.getCurrentTourist();
        ArrayList<Booking> bookings = new ArrayList<>(bookings(tourist));
        if(bookings.size() == 0){
            Text text = new Text();
            text.setFill(Paint.valueOf("#6b6a6a"));
            text.setFont(Font.font("System", FontPosture.ITALIC, 20));
            text.setText("No ha realizado ninguna reserva por el momento");
            vBoxReservas.getChildren().add(text);
        }
        try {
            for (Booking booking : bookings) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniBookingController miniBookingController = (MiniBookingController) applicationContext.getBean("miniBookingControllerPrototype");
                fxmlLoader.setController(miniBookingController);

                fxmlLoader.setController(miniBookingController);
                fxmlLoader.setLocation(miniBookingController.getClass().getResource("MiniBookingView.fxml"));
                HBox hbox = fxmlLoader.load();
                miniBookingController.setData(booking);
                vBoxReservas.getChildren().add(hbox);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Booking> bookings(Tourist tourist){
        List<Booking> bookings = tourist.getBookings();
        bookings.removeIf(booking -> booking.getDate().compareTo(LocalDate.now())<0);
        bookings = bookings.stream().sorted(Comparator.comparing(Booking::getDate)).collect(Collectors.toList());
        return bookings;
    }
}