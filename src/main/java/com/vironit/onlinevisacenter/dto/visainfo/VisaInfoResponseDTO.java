package com.vironit.onlinevisacenter.dto.visainfo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
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
