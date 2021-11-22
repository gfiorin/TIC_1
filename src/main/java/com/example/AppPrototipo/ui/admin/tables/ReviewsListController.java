package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Complaint;
import com.example.AppPrototipo.business.managers.ReviewMgr;
import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Review;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.ui.admin.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReviewsListController {

    private final ReviewMgr reviewMgr;

    @FXML
    private TableView<Review> reviewsTable;

    @FXML
    private TableColumn<Review, Integer> id;

    @FXML
    private TableColumn<Review, String> comment;

    @FXML
    private TableColumn<Review, String> rating;

    @FXML
    private TableColumn<Review, Experience> experience;

    @FXML
    private TableColumn<Review, Tourist> tourist;

    @FXML
    private Button checkReviewBtn;

    @FXML
    private Button goBackBtn;

    public ReviewsListController(ReviewMgr reviewMgr) {
        this.reviewMgr = reviewMgr;
    }

    @FXML
    public void initialize(){
        reviewsTable.setItems(getReviews());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        tourist.setCellValueFactory(new PropertyValueFactory<>("tourist"));
    }

    private ObservableList<Review> getReviews() {
        List<Review> reviewsList = reviewMgr.findAll();
        return FXCollections.observableArrayList(reviewsList);
    }

    @FXML
    void selectReview(ActionEvent event) throws Exception{
        Review review = reviewsTable.getSelectionModel().getSelectedItem();

        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ReviewViewController.class.getResourceAsStream("ReviewView.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        ReviewViewController reviewViewController = fxmlLoader.getController();

        newStage.show();

        reviewViewController.setReview(review);

        oldStage.close();

    }

    @FXML
    void goBackToAdminView(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AdminController.class.getResourceAsStream("AdminPanel.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
    }

    @FXML
    void close(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
