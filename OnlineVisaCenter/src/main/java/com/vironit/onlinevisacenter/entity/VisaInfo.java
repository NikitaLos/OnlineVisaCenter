package com.vironit.onlinevisacenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "visa_info", schema = "visa_center")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VisaInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visa_id")
    private Visa visa;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "date_of_receiving")
    private LocalDate dateOfReceiving;

    @Column(name = "name_client")
    private String nameOfClient;

    @Column(name = "surname_client")
    private String surnameOfClient;

    @Column(name = "days_of_residence")
    private Integer numOfDaysResidence;

    @Column(name = "visa_path_on_server")
    private String visaPathOnServer;


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


    public String getVisaPathOnServer() {
        return visaPathOnServer;
    }

    public void setVisaPathOnServer(String visaPathOnServer) {
        this.visaPathOnServer = visaPathOnServer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisaInfo visaInfo = (VisaInfo) o;

        if (id != null ? !id.equals(visaInfo.id) : visaInfo.id != null) return false;
        if (visa != null ? !visa.equals(visaInfo.visa) : visaInfo.visa != null) return false;
        if (dateFrom != null ? !dateFrom.equals(visaInfo.dateFrom) : visaInfo.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(visaInfo.dateTo) : visaInfo.dateTo != null) return false;
        if (dateOfReceiving != null ? !dateOfReceiving.equals(visaInfo.dateOfReceiving) : visaInfo.dateOfReceiving != null)
            return false;
        if (nameOfClient != null ? !nameOfClient.equals(visaInfo.nameOfClient) : visaInfo.nameOfClient != null)
            return false;
        if (surnameOfClient != null ? !surnameOfClient.equals(visaInfo.surnameOfClient) : visaInfo.surnameOfClient != null)
            return false;
        if (numOfDaysResidence != null ? !numOfDaysResidence.equals(visaInfo.numOfDaysResidence) : visaInfo.numOfDaysResidence != null)
            return false;
        return visaPathOnServer != null ? visaPathOnServer.equals(visaInfo.visaPathOnServer) : visaInfo.visaPathOnServer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (visa != null ? visa.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (dateOfReceiving != null ? dateOfReceiving.hashCode() : 0);
        result = 31 * result + (nameOfClient != null ? nameOfClient.hashCode() : 0);
        result = 31 * result + (surnameOfClient != null ? surnameOfClient.hashCode() : 0);
        result = 31 * result + (numOfDaysResidence != null ? numOfDaysResidence.hashCode() : 0);
        result = 31 * result + (visaPathOnServer != null ? visaPathOnServer.hashCode() : 0);
        return result;
    }
}
