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

    public void addUser(String name, String surname, String username, String email, String password) throws InvalidInformation, UserAlreadyExsists {

        if (name == null || name.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre válido");

        }

        if (surname == null || surname.isBlank()){

            throw new InvalidInformation("Por favor ingrese un apellido válido");

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

        for (char ch: surname.toCharArray()) {
            if(Character.isDigit(ch)){
                throw new InvalidInformation("El apellido no puede contener numeros");
            }
        }

        if (userRepository.findOneByEmail(email) != null) {
            throw new UserAlreadyExsists("El usuario ya ha sido registrado en el sistema");
        }

        User userToAdd = new User(name, surname, username, email, password);

        userRepository.save(userToAdd);

        //estaria bueno mostrar algo del estilo, el usuario ha sido registrado con exito

    }

    public void userLogIn(String email, String password) throws InvalidInformation {

        User user = userRepository.findOneByEmail(email);

        if (user == null) {

            throw new InvalidInformation("El usuario no existe");

        }

        // estaria bueno mostrar algo que diga "Bienvenido (username)" y despues de la opcion de salir

    }

}
