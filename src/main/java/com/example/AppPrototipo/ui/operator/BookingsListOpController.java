package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.BookingMgr;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
public class BookingsListOpController {

    @FXML
    private TableView<Booking> bookingsTable;

    @FXML
    private TableColumn<Booking, String> tourist;

    @FXML
    private TableColumn<Booking, LocalDate> date;

    @FXML
    private TableColumn<Booking, Experience> experience;

    @FXML
    private TableColumn<Booking, Time> time;

    @FXML
    private TableColumn<Booking, Integer> amount;

    private final BookingMgr bookingMgr;
    private final UserMgr userMgr;
    private final ExperienceMgr experienceMgr;

    public BookingsListOpController(BookingMgr bookingMgr, UserMgr userMgr, ExperienceMgr experienceMgr) {
        this.bookingMgr = bookingMgr;
        this.userMgr = userMgr;
        this.experienceMgr = experienceMgr;
    }

    @FXML
    public void initialize(){
        bookingsTable.setItems(getBookings());

        tourist.setCellValueFactory(new PropertyValueFactory<>("tourist"));
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private ObservableList<Booking> getBookings() {
        TourOperator tourOperator = ((Operator) userMgr.getCurrentUser()).getTourOperator();
        List<Experience> experienceList = experienceMgr.findByTourOperator(tourOperator);
        List<Booking> bookingList = new LinkedList<>();
        for (Experience experience : experienceList){
            List<Booking> bookingListPre = bookingMgr.findByExperience(experience);
            bookingList.addAll(bookingListPre);
        }
        return FXCollections.observableArrayList(bookingList);
    }

}
