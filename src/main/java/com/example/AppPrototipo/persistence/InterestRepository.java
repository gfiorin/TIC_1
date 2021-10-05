package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Interest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends CrudRepository<Interest, Integer> {

    List<Interest> findAll();

}
