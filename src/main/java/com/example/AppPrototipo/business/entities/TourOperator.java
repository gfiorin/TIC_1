package com.example.AppPrototipo.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "touristOperators")
public class TourOperator {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "companyName", unique = true)
    private String companyName;

    @Column(name = "fantasyName", unique = true)
    private String fantasyName;

    @Column(name = "linkToWeb", unique = true)
    private String linkToWeb;

    @Column(name = "contactName")
    private String contactName;

    @Column(name = "contactPhone", unique = true)
    private String contactPhone;

    @Column(name = "contactPosition")
    private String contactPosition;

    @Column(name = "contactEmail", unique = true)
    private String contactEmail;

    @Column(name = "authorized")
    private boolean authorized;

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
