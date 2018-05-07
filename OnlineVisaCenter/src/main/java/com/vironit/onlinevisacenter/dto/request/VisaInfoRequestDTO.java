package com.vironit.onlinevisacenter.dto.request;

import java.time.LocalDate;

public class VisaInfoRequestDTO {

    private Integer id;

    private Integer visaId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Integer numOfDaysResidence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisaId() {
        return visaId;
    }

    public void setVisaId(Integer visaId) {
        this.visaId = visaId;
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
