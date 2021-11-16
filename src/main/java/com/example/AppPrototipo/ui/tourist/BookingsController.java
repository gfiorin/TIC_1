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
import javafx.scene.text.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tourist tourist = (Tourist) userMgr.getCurrentUser();
        ArrayList<Booking> bookings = new ArrayList<>(bookings(tourist));
        try {
            for (Booking booking : bookings) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                ApplicationContext applicationContext = AppPrototipoApplication.getContext();
                MiniBookingController miniBookingController = (MiniBookingController) applicationContext.getBean("miniExperienceControllerPrototype");
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
        return bookingMgr.findAll();
    }

}