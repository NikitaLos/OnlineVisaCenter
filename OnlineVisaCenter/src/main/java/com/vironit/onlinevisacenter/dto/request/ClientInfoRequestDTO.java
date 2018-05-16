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

    @NotNull(message = "name can not be null")
    @Size(min = 2, message = "name must have {min} characters minimum")
    private String name;

    @NotNull(message = "surname can not be null")
    @Size(min = 2, message = "name must have {min} characters minimum")
    private String surname;

    @NotNull(message = "sex can not be null")
    private String sex;

    @NotNull(message = "phoneNumber can not be null")
    @Size(min = 5, max = 7, message = "phoneNumber mut have from {min} to {max} characters")
    private String phoneNumber;

    @NotNull(message = "photoPath can not be null")
    private String photoPath;

    @NotNull(message = "dateOfBirth can not be null")
    @Past(message = "dateOfBirth must be in past")
    private LocalDate dateOfBirth;

    @Valid
    private PassportRequestDTO passport;

    @NotNull(message = "aim of visit can not be null")
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
