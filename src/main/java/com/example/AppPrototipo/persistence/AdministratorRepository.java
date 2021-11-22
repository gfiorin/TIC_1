package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

    Administrator findOneByEmail(String email);

    Administrator findOneByUsername(String username);

    List<Administrator> findAll();

    void deleteAdministratorById(Integer id);

}
