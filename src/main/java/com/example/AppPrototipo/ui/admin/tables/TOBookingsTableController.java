package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TOBookingsTableController {

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

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button applyFilterBtn;

    @FXML
    private Button cleanFilterBtn;

    private TourOperator tourOperator;

    private final ExperienceMgr experienceMgr;

    public TOBookingsTableController(ExperienceMgr experienceMgr) {
        this.experienceMgr = experienceMgr;
    }

    @FXML
    public void initialize(){
        if (tourOperator != null){
            bookingsTable.setItems(getBookings());

            tourist.setCellValueFactory(new PropertyValueFactory<>("tourist"));
            experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        }
    }

    @FXML
    public void applyFilter() {

        if (dateTo.getValue()!=null && dateFrom.getValue()!=null) {

            bookingsTable.getItems().clear();
            List<Booking> bookings = new ArrayList<>();

            for (Booking booking : getBookings()) {
                if (booking.getDate() != null) {
                    LocalDate bookingDate = LocalDate.ofInstant(booking.getDate().toInstant(), ZoneId.systemDefault());
                    if (bookingDate.compareTo(dateTo.getValue())<=0 && bookingDate.compareTo(dateFrom.getValue())>=0) {
                        bookings.add(booking);
                    }
                }
            }

            bookings = bookings.stream().sorted(Comparator.comparing(Booking::getDate)).collect(Collectors.toList());

            bookingsTable.setItems(FXCollections.observableArrayList(bookings));

            tourist.setCellValueFactory(new PropertyValueFactory<>("tourist"));
            experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        }
    }

    @FXML
    public void cleanFilter() {
        bookingsTable.getItems().clear();
        initialize();
    }


    private ObservableList<Booking> getBookings() {
        List<Experience> experienceList = experienceMgr.findByTourOperator(tourOperator);
        List<Booking> bookingList = new LinkedList<>();
        for (Experience experience : experienceList) {
            List<Booking> bookingListPre = experienceMgr.findByExperience(experience);
            bookingList.addAll(bookingListPre);
        }
        return FXCollections.observableArrayList(bookingList);
    }

    @FXML
    void goBackToTOTable(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(TouristOperatorsTableController.class.getResourceAsStream("TouristOperatorsTable.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
    }

    public void setTourOperator(TourOperator tourOperator){
        this.tourOperator = tourOperator;
        initialize();
    }

}
