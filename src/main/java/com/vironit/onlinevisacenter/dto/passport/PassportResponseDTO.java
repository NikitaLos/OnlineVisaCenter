package com.vironit.onlinevisacenter.dto.passport;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportResponseDTO {
    private Integer id;

    private String number;

    private String countryOfResidence;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfReceiving;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfEnding;
}
