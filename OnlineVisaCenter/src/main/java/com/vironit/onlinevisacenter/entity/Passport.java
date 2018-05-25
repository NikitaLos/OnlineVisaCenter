package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "country")
    private String countryOfResidence;

    @Column(name = "date_of_receiving")
    private LocalDate dateOfReceiving;

    @Column(name = "date_of_ending")
    private LocalDate dateOfEnding;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public LocalDate getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(LocalDate dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public LocalDate getDateOfEnding() {
        return dateOfEnding;
    }

    public void setDateOfEnding(LocalDate dateOfEnding) {
        this.dateOfEnding = dateOfEnding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (id != null ? !id.equals(passport.id) : passport.id != null) return false;
        if (number != null ? !number.equals(passport.number) : passport.number != null) return false;
        if (countryOfResidence != null ? !countryOfResidence.equals(passport.countryOfResidence) : passport.countryOfResidence != null)
            return false;
        if (dateOfReceiving != null ? !dateOfReceiving.equals(passport.dateOfReceiving) : passport.dateOfReceiving != null)
            return false;
        return dateOfEnding != null ? dateOfEnding.equals(passport.dateOfEnding) : passport.dateOfEnding == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (countryOfResidence != null ? countryOfResidence.hashCode() : 0);
        result = 31 * result + (dateOfReceiving != null ? dateOfReceiving.hashCode() : 0);
        result = 31 * result + (dateOfEnding != null ? dateOfEnding.hashCode() : 0);
        return result;
    }
}
