package com.vironit.onlinevisacenter.dto.request;

import com.vironit.onlinevisacenter.dto.validation.DateOfBirth;
import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@DateOfBirth(groups = DateValidateGroup.class)
public class ClientInfoRequestDTO {

    private Integer id;

    @NotNull(message = "{client_info.name.null}")
    @Size(min = 2, message = "{client_info.name.size}")
    private String name;

    @NotNull(message = "{client_info.surname.null}")
    @Size(min = 2, message = "{client_info.surname.size}")
    private String surname;

    @NotNull(message = "{client_info.sex.null}")
    private String sex;

    @NotNull(message = "{client_info.phone_number.null}")
    @Size(min = 5, max = 7, message = "{client_info.phone_number.size}")
    private String phoneNumber;

    @NotNull(message = "{client_info.photo_path.null}")
    private String photoPath;

    @NotNull(message = "{client_info.date_of_birth.null}")
    @Past(message = "{client_info.date_of_birth}")
    private LocalDate dateOfBirth;

    @Valid
    private PassportRequestDTO passport;

    @NotNull(message = "{client_info.aim_of_visit}")
    private AimOfVisit aimOfVisit;
}
