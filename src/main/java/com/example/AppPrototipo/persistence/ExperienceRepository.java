package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    Experience findById(int id);

    List<Experience> findAll();

}
