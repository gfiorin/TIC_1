package com.example.AppPrototipo.ui.tourist;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.managers.UserMgr;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ProfileController {

    private final UserMgr userMgr;

    private List<Interest> listaIntereses;

    @FXML
    private Button editarBtn;

    @FXML
    private Text fechaNacimiento;

    @FXML
    private VBox vBoxIntereses;

    @FXML
    private Text mail;

    @FXML
    private Text nombre;

    @FXML
    private Text nombreUsuario;

    @FXML
    private Text pais;

    @FXML
    private Text telefono;

    public ProfileController(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    @FXML
    private void initialize(){
        Tourist tourist = userMgr.getCurrentTourist();
        listaIntereses = tourist.getInterests();
        populacionDeCampos(tourist);
    }

    private void populacionDeCampos(Tourist tourist) {
        nombre.setText(tourist.getName());
        nombreUsuario.setText(tourist.getUsername());
        mail.setText(tourist.getEmail());
        String formatoFecha = "dd/MM/yyyy";
        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern(formatoFecha);
        fechaNacimiento.setText(fechaFormatter.format(tourist.getDateOfBirth()));
        telefono.setText(tourist.getCellphone());
        pais.setText(tourist.getCountry().getName());
        for (Interest interest : listaIntereses) {
            Text text = new Text();
            text.setFont(Font.font("System",20));
            text.setText("• " + interest.getName());
            vBoxIntereses.getChildren().add(text);
        }
    }

    @FXML
    void editarIntereses(ActionEvent event) {

    }
}
