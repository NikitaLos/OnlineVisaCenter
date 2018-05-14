package com.vironit.onlinevisacenter.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PassportResponseDTO {
    private Integer id;

    private String number;

    private String countryOfResidence;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfReceiving;

    @JsonFormat(pattern = "MM/dd/yyyy")
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
}
