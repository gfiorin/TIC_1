package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {

}
