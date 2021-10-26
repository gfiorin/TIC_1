package com.example.AppPrototipo.business;

import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.TourOperatorAlreadyExists;
import com.example.AppPrototipo.persistence.OperatorsRepository;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TourOperatorMgr {

    private final TourOperatorRepository tourOperatorRepository;
    private final OperatorsRepository operatorsRepository;

    public TourOperatorMgr(TourOperatorRepository tourOperatorRepository, OperatorsRepository operatorsRepository){
        this.operatorsRepository = operatorsRepository;
        this.tourOperatorRepository = tourOperatorRepository;
    }

    public void addTourOperator(String companyName, String fantasyName, String linkToWeb, String contactName, String contactPhone, String contactPosition, String contactEmail, boolean authorized) throws InvalidInformation, TourOperatorAlreadyExists {

        if (companyName == null || companyName.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre de compañía válido");

        }

        if (fantasyName == null || fantasyName.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre de fantasía válido");

        }

        if (linkToWeb == null || linkToWeb.isBlank()){

            throw new InvalidInformation("Por favor ingrese un link válido");

        }

        if (contactName == null || contactName.isBlank()){

            throw new InvalidInformation("Por favor ingrese un nombre de contacto válido");

        }

        if (contactPhone == null || contactPhone.isBlank()){

            throw new InvalidInformation("Por favor ingrese un numero de telefono de contacto válido");

        }

        if (contactPosition == null || contactPosition.isBlank()){

            throw new InvalidInformation("Por favor ingrese un cargo de contacto válido");

        }

        if (contactEmail == null || contactEmail.isBlank()){

            throw new InvalidInformation("Por favor ingrese un email de contacto válido");

        }

        if (tourOperatorRepository.findOneByCompanyName(companyName) != null) {
            throw new TourOperatorAlreadyExists("La compañia ya ha sido registrado en el sistema");
        }

        if (tourOperatorRepository.findOneByFantasyName(fantasyName) != null) {
            throw new TourOperatorAlreadyExists("El nombre de fantasia de la compañia ya ha sido registrado en el sistema");
        }

        if (tourOperatorRepository.findOneByContactPhone(contactPhone) != null) {
            throw new TourOperatorAlreadyExists("El telefono de contacto de la compañia ya ha sido registrado en el sistema");
        }

        if (tourOperatorRepository.findOneByContactEmail(contactEmail) != null) {
            throw new TourOperatorAlreadyExists("El email de contacto de la compañia ya ha sido registrado en el sistema");
        }

        if (tourOperatorRepository.findOneByLinkToWeb(linkToWeb) != null) {
            throw new TourOperatorAlreadyExists("El link de la compañia ya ha sido registrado en el sistema");
        }

        TourOperator tourOperatorToAdd = new TourOperator(companyName, fantasyName, linkToWeb, contactName, contactPhone, contactPosition, contactEmail, authorized);

        tourOperatorRepository.save(tourOperatorToAdd);

    }

    @Transactional
    public void changeAuthorizationOfTouristOperator(Integer tourOperatorId){
        TourOperator tourOperator = tourOperatorRepository.findOneById(tourOperatorId);
        if (tourOperator.isAuthorized()){
            tourOperator.disableTourOperator();
        }
        else {
            tourOperator.enableTourOperator();
        }
    }

}
