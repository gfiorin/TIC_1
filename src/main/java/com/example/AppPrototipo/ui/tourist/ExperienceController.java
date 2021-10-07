package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.persistence.ExperienceRepository;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

@Component
public class ExperienceController {

    @FXML
    ImageView imageView;

    private final ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @FXML
    private void initialize(){
        Experience experience = experienceRepository.findById(1);
        System.out.println(experience);
    }
}
