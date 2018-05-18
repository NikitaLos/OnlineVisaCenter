package com.vironit.onlinevisacenter.dto.request;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PassportRequestDTO {

    private Integer id;

    @NotNull(message = "number can not be null")
    @Size(min = 8, message = "number must have {min} characters minimum")
    private String number;

    @NotNull(message = "countryOfResidence can not be null")
    @Size(min = 2, message = "country must have {min} characters minimum")
    private String countryOfResidence;

    @NotNull(message = "dateOfReceiving can not be null")
    @Past(message = "dateOfReceiving must be in past")
    private LocalDate dateOfReceiving;

    @NotNull(message = "dateOfEnding can not be null")
    @Future(message = "dateOfEnding must be in future")
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
