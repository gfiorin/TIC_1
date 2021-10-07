package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.business.entities.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    Experience findOneByTitle(String title);

}
