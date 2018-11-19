package com.vironit.onlinevisacenter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "visa_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisaInfo extends AbstractIdentifiableEntity implements Serializable {

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
}
