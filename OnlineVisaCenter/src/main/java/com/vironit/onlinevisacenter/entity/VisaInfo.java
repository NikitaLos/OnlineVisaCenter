package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "visa_info", schema = "visa_center")
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "visaInfo")
    private Application application;


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
