package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Department;
import com.example.AppPrototipo.business.entities.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}
