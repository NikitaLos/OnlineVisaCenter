package com.vironit.onlinevisacenter.entity;

import java.time.LocalDate;
import java.util.List;

public class VisaInfo implements Identified<Integer>{

    private Integer id;
    private Visa visa;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate dateOfReceiving;
    private String nameOfClient;
    private String surnameOfClient;
    private Integer numOfDaysResidence;
    private Application application;
    private String visaPathOnServer;
    private List<ClientDocument> clientDocuments;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(LocalDate dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getSurnameOfClient() {
        return surnameOfClient;
    }

    public void setSurnameOfClient(String surnameOfClient) {
        this.surnameOfClient = surnameOfClient;
    }

    public Integer getNumOfDaysResidence() {
        return numOfDaysResidence;
    }

    public void setNumOfDaysResidence(Integer numOfDaysResidence) {
        this.numOfDaysResidence = numOfDaysResidence;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getVisaPathOnServer() {
        return visaPathOnServer;
    }

    public void setVisaPathOnServer(String visaPathOnServer) {
        this.visaPathOnServer = visaPathOnServer;
    }

    public List<ClientDocument> getClientDocuments() {
        return clientDocuments;
    }

    public void setClientDocuments(List<ClientDocument> clientDocuments) {
        this.clientDocuments = clientDocuments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisaInfo visaInfo = (VisaInfo) o;

        return application != null ? application.equals(visaInfo.application) : visaInfo.application == null;
    }

    @Override
    public int hashCode() {
        return application != null ? application.hashCode() : 0;
    }
}
