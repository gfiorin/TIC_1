package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer>{
}
