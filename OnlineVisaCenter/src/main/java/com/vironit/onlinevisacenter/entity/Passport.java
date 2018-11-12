package com.vironit.onlinevisacenter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
@Data
@EqualsAndHashCode(callSuper = true)
public class Passport extends AbstractIdentifiableEntity implements Serializable {

    @Column(name = "number")
    private String number;

    @Column(name = "country")
    private String countryOfResidence;

    @Column(name = "date_of_receiving")
    private LocalDate dateOfReceiving;

    @Column(name = "date_of_ending")
    private LocalDate dateOfEnding;
}
