package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Review;
import com.example.AppPrototipo.persistence.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewMgr {

    private final ReviewRepository reviewRepository;

    public ReviewMgr(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

}
