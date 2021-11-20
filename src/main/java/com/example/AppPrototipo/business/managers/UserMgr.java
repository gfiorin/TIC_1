package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.*;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.TouristRepository;
import com.example.AppPrototipo.persistence.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserMgr {

    private final TouristRepository touristRepository;
    private final UserRepository userRepository;
    private int currentUserId;

    public UserMgr(TouristRepository touristRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.touristRepository = touristRepository;
    }

    public void addTourist(String name, String username, String email, String password, LocalDate dateOfBirth, String cellphone, Country country, List<Interest> interests, String documentType, String documentNumber) throws InvalidInformation, UserAlreadyExsists {

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

            touristToAdd = new Tourist(name,username,email,password, dateOfBirth, cellphone, country, interests);

        }
        else {

            touristToAdd = new Tourist(name,username,email,password, dateOfBirth, cellphone, country, interests, documentType, documentNumber);

        }

        touristRepository.save(touristToAdd);
    }


    @Transactional
    public User userLogIn(String emailOrUsername, String password) throws InvalidInformation {

        User user = userRepository.findOneByEmail(emailOrUsername);

        if (user == null) {

            user = userRepository.findOneByUsername(emailOrUsername);

            if (user == null) {

                throw new InvalidInformation("El usuario no existe");

            }

        }

        if (user.getPassword().equals(password)){
            currentUserId = user.getId();
            return  user;
        }
        else{
            throw new InvalidInformation("Contraseña incorrecta");

        }

    }

    public void updateTourist(Tourist tourist){
        touristRepository.save(tourist);
    }

    @Transactional
    public User getCurrentUser() {
        return userRepository.findById(currentUserId).get();
    }

    @Transactional
    public List<Experience> getCurrentUserLiked() {
        return ((Tourist) getCurrentUser()).getLiked();
    }

    @Transactional
    public List<Interest> getCurrentUserInterests() {
        return ((Tourist) getCurrentUser()).getInterests();
    }

    @Transactional
    public Tourist getCurrentTourist() {
        Tourist tourist = (Tourist) userRepository.findById(currentUserId).get();
        Hibernate.initialize(tourist.getLiked());
        Hibernate.initialize(tourist.getInterests());
        Hibernate.initialize(tourist.getBookings());
        return tourist;
    }
}
