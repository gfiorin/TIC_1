package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    List<Department> findAll();

}
