package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.ExperienceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceTypeRepository extends CrudRepository<ExperienceType, Integer> {

    ExperienceType findOneByName(String name);

}
