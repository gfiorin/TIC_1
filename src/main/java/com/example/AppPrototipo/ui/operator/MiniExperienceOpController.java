package com.example.AppPrototipo.ui.operator;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.managers.ExperienceMgr;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class MiniExperienceOpController {

    private final ExperienceMgr experienceMgr;

    @FXML
    private Text nombreExperiencia;

    @FXML
    private Text cantidadReservas;

    @FXML
    private Text estado;

    @FXML
    private ImageView experienciaImg;

    public MiniExperienceOpController(ExperienceMgr experienceMgr) {
        this.experienceMgr = experienceMgr;
    }

    public void setData(Experience experience){

        experience = experienceMgr.getCurrentExperience(experience.getId());

        String e;
        if (experience.isAuthorized()) e = "Habilitada";
        else e = "Deshabilitada";

        nombreExperiencia.setText(experience.getTitle());
        cantidadReservas.setText(String.valueOf(experience.getBookings().size()));
        estado.setText(e);
        if (!experience.getImages().isEmpty()){
            Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
            experienciaImg.setImage(image);
        }
    }
}
