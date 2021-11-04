package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Complaint;
import com.example.AppPrototipo.persistence.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintMgr {

    private final ComplaintRepository complaintRepository;

    public ComplaintMgr(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> findAll(){
        return complaintRepository.findAll();
    }

}
