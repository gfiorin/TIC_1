package com.example.AppPrototipo.ui.admin.tables;

import com.example.AppPrototipo.AppPrototipoApplication;
import com.example.AppPrototipo.business.entities.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ReviewViewController {

    @FXML
    private Button goBack;

    @FXML
    private Label email;

    @FXML
    private Label experience;

    @FXML
    private Label raiting;

    @FXML
    private Label comment; // tengo que hacer que el comentario se estire para abajo aca y en las complaints TODO

    private Review review;

    @FXML
    public void initialize(){
        if (review != null){
            email.setText(review.getTourist().getEmail());
            experience.setText(review.getExperience().getTitle());
            raiting.setText(String.valueOf(review.getRating()));
            comment.setText(review.getComment());
        }
    }

    public void setReview(Review review){
        this.review = review;
        initialize();
    }

    @FXML
    void goBackToReviewTable(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage oldStage  = (Stage) source.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AppPrototipoApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ReviewsListController.class.getResourceAsStream("ReviewsList.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        oldStage.close();
    }

}
