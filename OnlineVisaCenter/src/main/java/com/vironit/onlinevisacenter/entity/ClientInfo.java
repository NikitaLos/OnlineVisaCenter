package com.vironit.onlinevisacenter.entity;

import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client_info")
public class ClientInfo implements Identified<Integer>{

    @Id
    private Integer id;
    private String name;
    private String surname;
    private String sex;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Application application;

    private Passport passport;
    private String photoPathOnServer;
    private AimOfVisit aimOfVisit;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getPhotoPathOnServer() {
        return photoPathOnServer;
    }

    public void setPhotoPathOnServer(String photoPathOnServer) {
        this.photoPathOnServer = photoPathOnServer;
    }

    public AimOfVisit getAimOfVisit() {
        return aimOfVisit;
    }

    public void setAimOfVisit(AimOfVisit aimOfVisit) {
        this.aimOfVisit = aimOfVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientInfo that = (ClientInfo) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        if (photoPathOnServer != null ? !photoPathOnServer.equals(that.photoPathOnServer) : that.photoPathOnServer != null)
            return false;
        return aimOfVisit == that.aimOfVisit;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (photoPathOnServer != null ? photoPathOnServer.hashCode() : 0);
        result = 31 * result + (aimOfVisit != null ? aimOfVisit.hashCode() : 0);
        return result;
    }
}
