package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.User;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMgr {

    private final UserRepository userRepository;

    public UserMgr(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String name, String username, String email, String password) throws InvalidInformation, UserAlreadyExsists {

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

        if (userRepository.findOneByEmail(email) != null) {
            throw new UserAlreadyExsists("El usuario ya ha sido registrado en el sistema");
        }

        userRepository.save(new User(name, username, email, password));
    }

    public void userLogIn(String email, String password) throws InvalidInformation {

        User user = userRepository.findOneByEmail(email);

        if (user == null) {

            throw new InvalidInformation("El usuario no existe");

        }

        // estaria bueno mostrar algo que diga "Bienvenido (username)" y despues de la opcion de salir

    }

}
