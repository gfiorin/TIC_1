package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Operator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorsRepository extends CrudRepository<Operator, Integer> {

    Operator findOneByEmail(String email);
    Operator findOneByUsername(String username);
    List<Operator> findAll();

}
