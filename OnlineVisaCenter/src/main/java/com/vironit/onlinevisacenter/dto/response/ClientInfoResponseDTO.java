package com.vironit.onlinevisacenter.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientInfoResponseDTO {
    private Integer id;

    private String name;

    private String surname;

    private String sex;

    private String phoneNumber;

    private String photoPath;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    private PassportResponseDTO passport;

    private AimOfVisit aimOfVisit;
}
