package com.vironit.onlinevisacenter.dto.request;

import com.vironit.onlinevisacenter.dto.validation.DateOfBirth;
import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PassportRequestDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportRequestDTO passport) {
        this.passport = passport;
    }

    public AimOfVisit getAimOfVisit() {
        return aimOfVisit;
    }

    public void setAimOfVisit(AimOfVisit aimOfVisit) {
        this.aimOfVisit = aimOfVisit;
    }
}
