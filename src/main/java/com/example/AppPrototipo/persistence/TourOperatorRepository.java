package com.example.AppPrototipo.persistence;

import com.example.AppPrototipo.business.entities.TourOperator;
import com.example.AppPrototipo.business.entities.Tourist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourOperatorRepository extends CrudRepository<TourOperator, Integer>{

    TourOperator findOneByCompanyName(String companyName);
    List<TourOperator> findAll();

    TourOperator findOneByFantasyName(String fantasyName);

    TourOperator findOneByLinkToWeb(String linkToWeb);

    TourOperator findOneByContactEmail(String contactEmail);

    TourOperator findOneByContactPhone(String contactPhone);

    @Modifying
    @Query("update TourOperator to set to.authorized = true where to.id = :idTO")
    void enableTO(@Param("idTO") Integer idTO);

    @Modifying
    @Query("update TourOperator to set to.authorized = false where to.id = :idTO")
    void disableTO(@Param("idTO") Integer idTO);

}
