package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;

@Component
public class ExperienceController {

    @FXML
    ImageView imageViewPrincipal;

    @FXML
    ImageView imageViewMarker;

    private final ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @FXML
    private void initialize(){
        Image imageMarker = new Image(getClass().getResourceAsStream("/imgs/location.png"));
        imageViewMarker.setImage(imageMarker);

        Experience experience = experienceRepository.findById(1);
        Image image = new Image(new ByteArrayInputStream(experience.getImages().get(0).getImageData()));
        imageViewPrincipal.setImage(image);
    }
}
