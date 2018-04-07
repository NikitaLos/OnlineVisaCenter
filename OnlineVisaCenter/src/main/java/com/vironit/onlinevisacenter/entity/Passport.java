package com.vironit.onlinevisacenter.entity;

import java.time.LocalDate;

public class Passport implements Identified<Integer>{

    private Integer id;
    private String number;
    private String countryOfResidence;
    private LocalDate dateOfReceiving;
    private LocalDate dateOfEnding;

    @Override
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

        return number != null ? number.equals(passport.number) : passport.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
