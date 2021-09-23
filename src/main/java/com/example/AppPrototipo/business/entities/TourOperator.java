package com.example.AppPrototipo.business.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tourist_operators")
public class TourOperator {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "fantasy_name", unique = true)
    private String fantasyName;

    @Column(name = "link_to_web", unique = true)
    private String linkToWeb;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone", unique = true)
    private String contactPhone;

    @Column(name = "contact_position")
    private String contactPosition;

    @Column(name = "contact_email", unique = true)
    private String contactEmail;

    @Column(name = "authorized")
    private boolean authorized;

    @OneToMany
    private List<Experience> listOfExperiences;

    @OneToMany
    private List<Operator> listOfAuthorizedOperators;

    public TourOperator() {}

    public TourOperator(String companyName, String fantasyName, String linkToWeb, String contactName, String contactPhone, String contactPosition, String contactEmail, boolean authorized) {
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.linkToWeb = linkToWeb;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactPosition = contactPosition;
        this.contactEmail = contactEmail;
        this.authorized = authorized;
    }

    public void enableTourOperator(){
        this.authorized = true;
    }

    public void disableTourOperator(){
        this.authorized = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getLinkToWeb() {
        return linkToWeb;
    }

    public void setLinkToWeb(String linkToWeb) {
        this.linkToWeb = linkToWeb;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

}
