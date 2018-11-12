package com.vironit.onlinevisacenter.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisaInfoResponseDTO {

    private Integer id;

    private VisaResponseDTO visa;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateTo;

    private Integer numOfDaysResidence;
}
