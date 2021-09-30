package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Integer>{

    Tourist findOneByEmail(String email);

}
