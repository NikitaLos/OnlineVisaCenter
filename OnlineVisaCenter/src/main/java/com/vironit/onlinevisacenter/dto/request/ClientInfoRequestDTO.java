package com.vironit.onlinevisacenter.dto.request;

import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;

import java.time.LocalDate;

public class ClientInfoRequestDTO {
    private Integer id;

    private String name;

    private String surname;

    private String sex;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private PassportRequestDTO passport;

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
