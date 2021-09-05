package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    User findOneByEmail(String email);

}
