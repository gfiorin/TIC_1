package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findOneByName(String name);

}
