package com.vironit.onlinevisacenter.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class VisaInfoResponseDTO {

    private Integer id;

    private VisaResponseDTO visa;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateTo;

    private Integer numOfDaysResidence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VisaResponseDTO getVisa() {
        return visa;
    }

    public void setVisa(VisaResponseDTO visa) {
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

    public Integer getNumOfDaysResidence() {
        return numOfDaysResidence;
    }

    public void setNumOfDaysResidence(Integer numOfDaysResidence) {
        this.numOfDaysResidence = numOfDaysResidence;
    }
}
