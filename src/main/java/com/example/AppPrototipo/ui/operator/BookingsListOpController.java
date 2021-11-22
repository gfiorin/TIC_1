package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.*;
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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class BookingsListOpController {

    @FXML
    private TableView<BookingInformation> bookingsTable;

    @FXML
    private TableColumn<Booking, String> touristEmail;

    @FXML
    private TableColumn<Booking, Date> date;

    @FXML
    private TableColumn<Booking, String> experienceTitle;

    @FXML
    private TableColumn<Booking, Time> time;

    @FXML
    private TableColumn<Booking, Integer> amount;

    @FXML
    private TableColumn<Booking, String> touristCountry;

    @FXML
    private TableColumn<Booking, String> touristCellphone;

    private final UserMgr userMgr;
    private final ExperienceMgr experienceMgr;

    public BookingsListOpController(UserMgr userMgr, ExperienceMgr experienceMgr) {
        this.userMgr = userMgr;
        this.experienceMgr = experienceMgr;
    }

    @FXML
    public void initialize() {
        bookingsTable.setItems(getBookings());

        touristEmail.setCellValueFactory(new PropertyValueFactory<>("touristEmail"));
        experienceTitle.setCellValueFactory(new PropertyValueFactory<>("experienceTitle"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        touristCountry.setCellValueFactory(new PropertyValueFactory<>("touristCountry"));
        touristCellphone.setCellValueFactory(new PropertyValueFactory<>("touristCellphone"));
    }

    private ObservableList<BookingInformation> getBookings() {
        TourOperator tourOperator = ((Operator) userMgr.getCurrentUser()).getTourOperator();
        List<Experience> experienceList = experienceMgr.findByTourOperator(tourOperator);
        List<Booking> bookingList = new LinkedList<>();
        for (Experience experience : experienceList) {
            List<Booking> bookingListPre = experienceMgr.findByExperience(experience);
            bookingList.addAll(bookingListPre);
        }
        List<BookingInformation> bookingInformationList = new LinkedList<>();
        for (Booking booking: bookingList){
            bookingInformationList.add(new BookingInformation(booking.getTourist().getEmail(), booking.getTourist().getCellphone(), booking.getExperience().getTitle(), booking.getTourist().getCountry().getName(), booking.getDate(), booking.getTime(), booking.getAmount()));
        }
        return FXCollections.observableArrayList(bookingInformationList);
    }

    public class BookingInformation { //esta clase se dejo publica porque hibernate daba un error y no mostraba las cosas en la tabla

        private String touristEmail;

        private String experienceTitle;

        private String touristCellphone;

        private String touristCountry;

        private Date date;

        private Time time;

        private int amount;

        public BookingInformation(String touristEmail, String touristCellphone, String experienceTitle, String touristCountry, Date date, Time time, int amount) {
            this.touristEmail = touristEmail;
            this.experienceTitle = experienceTitle;
            this.touristCountry = touristCountry;
            this.touristCellphone = touristCellphone;
            this.date = date;
            this.time = time;
            this.amount = amount;
        }

        public String getTouristEmail() {
            return touristEmail;
        }

        public void setTouristEmail(String touristEmail) {
            this.touristEmail = touristEmail;
        }

        public String getExperienceTitle() {
            return experienceTitle;
        }

        public void setExperienceTitle(String experienceTitle) {
            this.experienceTitle = experienceTitle;
        }

        public String getTouristCellphone() {
            return touristCellphone;
        }

        public void setTouristCellphone(String touristCellphone) {
            this.touristCellphone = touristCellphone;
        }

        public String getTouristCountry() {
            return touristCountry;
        }

        public void setTouristCountry(String touristCountry) {
            this.touristCountry = touristCountry;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

    }

}
