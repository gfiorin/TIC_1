package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

    Administrator findOneByEmail(String email);

}
