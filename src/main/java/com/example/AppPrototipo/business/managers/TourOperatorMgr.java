package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.TourOperatorAlreadyExists;
import com.example.AppPrototipo.persistence.OperatorsRepository;
import com.example.AppPrototipo.persistence.TourOperatorRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TourOperatorMgr {

    private final TourOperatorRepository tourOperatorRepository;
    private final OperatorsRepository operatorsRepository;

    public TourOperatorMgr(TourOperatorRepository tourOperatorRepository, OperatorsRepository operatorsRepository){
        this.operatorsRepository = operatorsRepository;
        this.tourOperatorRepository = tourOperatorRepository;
    }

    public TourOperator addTourOperator(String companyName, String fantasyName, String linkToWeb, String contactName, String contactPhone, String contactPosition, String contactEmail, boolean authorized) throws InvalidInformation, TourOperatorAlreadyExists {

        if (companyName == null || companyName.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un nombre de compañía válido.");

        }

        if (fantasyName == null || fantasyName.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un nombre de fantasía válido.");

        }

        if (linkToWeb == null || linkToWeb.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un link válido.");

        }

        if (contactName == null || contactName.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un nombre de contacto válido.");

        }

        if (contactPhone == null || contactPhone.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un numero de teléfono de contacto válido.");

        }

        if (contactPosition == null || contactPosition.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un cargo de contacto válido.");

        }

        if (contactEmail == null || contactEmail.isBlank()){

            throw new InvalidInformation("Por favor, ingrese un email de contacto válido.");

        }

        if (tourOperatorRepository.findOneByCompanyName(companyName) != null) {
            throw new TourOperatorAlreadyExists("La compañía ya ha sido registrado en el sistema.");
        }

        if (tourOperatorRepository.findOneByFantasyName(fantasyName) != null) {
            throw new TourOperatorAlreadyExists("El nombre de fantasía de la compañía ya ha sido registrado en el sistema.");
        }

        if (tourOperatorRepository.findOneByContactPhone(contactPhone) != null) {
            throw new TourOperatorAlreadyExists("El teléfono de contacto de la compañía ya ha sido registrado en el sistema.");
        }

        if (tourOperatorRepository.findOneByContactEmail(contactEmail) != null) {
            throw new TourOperatorAlreadyExists("El email de contacto de la compañía ya ha sido registrado en el sistema.");
        }

        if (tourOperatorRepository.findOneByLinkToWeb(linkToWeb) != null) {
            throw new TourOperatorAlreadyExists("El link de la compañía ya ha sido registrado en el sistema.");
        }

        TourOperator tourOperatorToAdd = new TourOperator(companyName, fantasyName, linkToWeb, contactName, contactPhone, contactPosition, contactEmail, authorized);

        tourOperatorRepository.save(tourOperatorToAdd);

        return tourOperatorToAdd;
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

    public TourOperator findOneByCompanyName(String companyName){
        return tourOperatorRepository.findOneByCompanyName(companyName);
    }

    public List<TourOperator> findAll(){
        return tourOperatorRepository.findAll();
    }

    @Transactional
    public TourOperator getCurrentTourOperator(int id){
        TourOperator tourOperator = tourOperatorRepository.findById(id).get();
        Hibernate.initialize(tourOperator.getListOfExperiences());
        return tourOperator;
    }


}
