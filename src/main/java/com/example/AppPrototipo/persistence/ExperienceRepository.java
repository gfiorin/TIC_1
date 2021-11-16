package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.ExperienceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    Experience findOneById(int id);

    List<Experience> findAll();

    List<Experience> findByExperienceTypesContaining(List<ExperienceType> experienceTypes);


}
