package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.entities.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourOperatorRepository extends CrudRepository<TourOperator, Integer>{

    TourOperator findOneByCompanyName(String companyName);

}
