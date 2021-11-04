package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.persistence.OperatorsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperatorMgr {

    private final OperatorsRepository operatorsRepository;

    public OperatorMgr(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
    }

    public Operator findOneByEmail(String email){
        return operatorsRepository.findOneByEmail(email);
    }

    public Operator findOneByUsername(String username){
        return operatorsRepository.findOneByUsername(username);
    }

    public void save(Operator operator){
        operatorsRepository.save(operator);
    }

    public List<Operator> findAll(){
        return operatorsRepository.findAll();
    }

}
