package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.Administrator;
import com.example.AppPrototipo.business.entities.Operator;
import com.example.AppPrototipo.business.entities.Tourist;
import com.example.AppPrototipo.business.entities.User;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.TouristRepository;
import com.example.AppPrototipo.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserMgr {

    private final TouristRepository touristRepository;
    private final UserRepository userRepository;

    public UserMgr(TouristRepository touristRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.touristRepository = touristRepository;
    }

    public void addTourist(String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, String documentType, String documentNumber) throws InvalidInformation, UserAlreadyExsists {

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

        if (dateOfBirth == null){

            throw new InvalidInformation("Por favor ingrese una fecha de nacimiento valida");

        }

        if (cellphone == null || cellphone.isBlank()){

            throw new InvalidInformation("Por favor ingrese un telefono valido");

        }

        for (char ch: name.toCharArray()) {
            if(Character.isDigit(ch)){
                throw new InvalidInformation("El nombre no puede contener numeros");
            }
        }

        if (userRepository.findOneByEmail(email) != null) {
            throw new UserAlreadyExsists("El email ya ha sido registrado en el sistema");
        }

        if (userRepository.findOneByUsername(username) != null) {
            throw new UserAlreadyExsists("El nombre de usuario ya ha sido registrado en el sistema");
        }

        Tourist touristToAdd;

        if (documentNumber == null || documentNumber.isBlank() || documentType == null || documentType.isBlank()){

            touristToAdd = new Tourist(name,username,email,password, dateOfBirth, cellphone);

        }
        else {

            touristToAdd = new Tourist(name,username,email,password, dateOfBirth, cellphone, documentType, documentNumber);

        }

        touristRepository.save(touristToAdd); //estaria bueno mostrar algo del estilo, el usuario ha sido registrado con exito

    }

    public void addAdministrator() {

    }

    public void addOperator() {

    }

    public Class userLogIn(String emailOrUsername, String password) throws InvalidInformation {

        User user = userRepository.findOneByEmail(emailOrUsername);

        if (user == null) {

            user = userRepository.findOneByUsername(emailOrUsername); // es super ineficiente esto de buscar dos veces

            if (user == null) {

                throw new InvalidInformation("El usuario no existe");

            }

        }

        if (user.getPassword().equals(password)){

            if (user instanceof Tourist) {
                return Tourist.class;
            }
            else if (user instanceof Administrator) {
                return Administrator.class;
            }
            else {
                return Operator.class;
            }

        }
        else{

            throw new InvalidInformation("Contraseña incorrecta");

        }

    }

}
