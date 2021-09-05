package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.User;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMgr {

    private UserRepository userRepository;

    public void addUser(String name, String email) throws InvalidInformation, UserAlreadyExsists{

        if (name == null || name.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre válido");

        }

        for (char ch: name.toCharArray()) {
            if(Character.isDigit(ch)){
                throw new InvalidInformation("El nombre no puede contener numeros");
            }
        }

        if (email == null || email.isBlank()){

            throw new InvalidInformation("Por favor ingrese un email válido");

        }

        if (userRepository.findOneByEmail(email) != null) {
            throw new UserAlreadyExsists("El usuario ya ha sido registrado en el programa");
        }

        User userToAdd = new User(name,email);

        userRepository.save(userToAdd);

    }

}
