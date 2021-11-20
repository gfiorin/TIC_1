package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Department;
import com.example.AppPrototipo.persistence.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentMgr {

    private final DepartmentRepository departmentRepository;

    public DepartmentMgr(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

}
