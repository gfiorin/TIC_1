package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Image;
import com.example.AppPrototipo.persistence.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageMgr {

    private final ImageRepository imageRepository;

    public ImageMgr(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void addImage(Experience experience, byte[] imageData){
        Image image = new Image(imageData, experience);
        imageRepository.save(image);
    }

}
