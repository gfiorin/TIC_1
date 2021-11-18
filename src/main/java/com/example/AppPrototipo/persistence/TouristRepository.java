package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Experience;
import com.example.AppPrototipo.business.entities.Interest;
import com.example.AppPrototipo.business.entities.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Integer>{

    Tourist findOneByEmail(String email);

    List<Tourist> findAll();

    List<Interest> getInterests(Tourist tourist);

}
