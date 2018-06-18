package com.vironit.onlinevisacenter.dto.request;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PassportRequestDTO {

    private Integer id;

    @NotNull(message = "{passport.number.null}")
    @Size(min = 8, message = "{passport.number.size}")
    private String number;

    @NotNull(message = "{passport.country.null}")
    @Size(min = 2, message = "{passport.country.size}")
    private String countryOfResidence;

    @NotNull(message = "{passport.date_of_receiving.null}")
    @Past(message = "{passport.date_of_receiving}")
    private LocalDate dateOfReceiving;

    @NotNull(message = "{passport.date_of_ending.null}")
    @Future(message = "{passport.date_of_ending}")
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
