package com.vironit.onlinevisacenter.dto.request;


import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.dto.validation.VisaDate;
import com.vironit.onlinevisacenter.dto.validation.VisaDays;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@VisaDate(groups = DateValidateGroup.class)
@VisaDays(groups = DateValidateGroup.class)
public class VisaInfoRequestDTO {

    private Integer id;

    @NotNull(message = "visa can not be null")
    private Integer visaId;

    @NotNull(message = "dateFrom can not be null")
    @Future(message = "dateFrom must be in future")
    private LocalDate dateFrom;

    @NotNull(message = "dateTo can not be null")
    @Future(message = "dateTo must be in future")
    private LocalDate dateTo;

    @NotNull(message = "numOfDaysResidence can not be null")
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
