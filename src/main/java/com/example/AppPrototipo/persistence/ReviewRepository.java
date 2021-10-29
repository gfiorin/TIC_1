package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    List<Review> findAll();

}