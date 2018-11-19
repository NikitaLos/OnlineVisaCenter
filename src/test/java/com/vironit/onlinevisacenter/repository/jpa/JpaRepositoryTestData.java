package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.entity.enums.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class JpaRepositoryTestData {

    public Application prepareApplication(User user, Visa visa) {
        Application application = new Application();
        application.setUser(user);
        application.setVisaInfo(prepareVisaInfo(visa));
        application.setClientInfo(prepareClientInfo());
        application.setComments("Test Comments");
        return application;
    }

    public Country prepareCountry() {
        Country country = new Country();
        country.setName("Test Country");
        return country;
    }

    public DocumentType prepareDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Test Document Type");
        return documentType;
    }

    public User prepareUser() {
        User user = new User();
        user.setLogin("Test User");
        user.setEmail("Test Email");
        user.setPassword("Test Password");
        user.setRole(Role.CLIENT);
        return user;
    }

    public Visa prepareVisa(Country country, DocumentType documentType) {
        Visa visa = new Visa();
        visa.setType("Test Visa Type");
        visa.setPrice(999.);
        visa.setCountry(country);
        visa.setRequiredDocumentTypes(Collections.singletonList(documentType));
        return visa;
    }

    private Passport preparePassport() {
        Passport passport = new Passport();
        passport.setCountryOfResidence("Test Country");
        passport.setDateOfEnding(LocalDate.now());
        passport.setDateOfReceiving(LocalDate.now());
        passport.setNumber("Test Number");
        return passport;
    }

    private VisaInfo prepareVisaInfo(Visa visa) {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setVisaPathOnServer("Test Visa Path");
        visaInfo.setVisa(visa);
        visaInfo.setDateOfReceiving(LocalDate.now());
        visaInfo.setDateFrom(LocalDate.now());
        visaInfo.setDateTo(LocalDate.now());
        visaInfo.setNumOfDaysResidence(5);
        visaInfo.setNameOfClient("Test Name");
        visaInfo.setSurnameOfClient("Test Surname");
        return visaInfo;
    }

    private ClientInfo prepareClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setPassport(preparePassport());
        clientInfo.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfo.setDateOfBirth(LocalDate.now());
        clientInfo.setPhoneNumber("Test Phone Number");
        clientInfo.setPhotoPathOnServer("Test Photo Path");
        clientInfo.setName("Test Name");
        clientInfo.setSurname("Test Surname");
        clientInfo.setSex("Men");
        return clientInfo;
    }
}
