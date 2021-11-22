package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.AdministratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministratorMgr {

    private final AdministratorRepository administratorRepository;

    public AdministratorMgr(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public void addAdministrator(String name, String email, String username, String password) throws InvalidInformation, UserAlreadyExsists {

        if (name == null || name.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre válido");

        }

        if (username == null || username.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre de usuario válido");

        }

        if (email == null || email.isBlank()){

            throw new InvalidInformation("Por favor ingrese un email válido");

        }

        if (password == null || password.isBlank() || password.length() < 6){

            throw new InvalidInformation("La clave debe tener al menos 6 caracteres");

        }

        for (char ch: name.toCharArray()) {
            if(Character.isDigit(ch)){
                throw new InvalidInformation("El nombre no puede contener numeros");
            }
        }

        if (administratorRepository.findOneByEmail(email) != null) {
            throw new UserAlreadyExsists("El email ya ha sido registrado en el sistema");
        }

        if (administratorRepository.findOneByUsername(username) != null) {
            throw new UserAlreadyExsists("El nombre de usuario ya ha sido registrado en el sistema");
        }

        Administrator administratorToAdd = new Administrator(name,username,email,password);

        administratorRepository.save(administratorToAdd);

    }

    public List<Administrator> findAll(){
        return administratorRepository.findAll();
    }

    @Transactional
    public void deleteAdministratorById(Integer id){
        administratorRepository.deleteAdministratorById(id);
    }

}
