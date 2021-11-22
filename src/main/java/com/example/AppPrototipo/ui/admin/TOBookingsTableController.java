package com.example.AppPrototipo.ui.admin;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.LinkedList;
import java.util.List;

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
    //todo
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

    private ObservableList<Booking> getBookings() {
        List<Experience> experienceList = experienceMgr.findByTourOperator(tourOperator);
        List<Booking> bookingList = new LinkedList<>();
        for (Experience experience : experienceList){
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

        Parent root = fxmlLoader.load(AdminController.class.getResourceAsStream("TouristOperatorsTable.fxml"));
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
