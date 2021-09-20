package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Country;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findOneByName();




}
