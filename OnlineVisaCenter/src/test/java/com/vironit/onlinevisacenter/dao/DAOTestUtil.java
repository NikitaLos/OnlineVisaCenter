package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.entity.enums.Role;

import java.time.LocalDate;

class DAOTestUtil {

    static Application prepareApplicationForInsert() {
        Application application = new Application();
        application.setUser(prepareUser());
        application.setVisaInfo(prepareVisaInfo());
        application.setClientInfo(prepareClientInfo());
        application.setComments("Test Comments");
        return application;
    }

    static Country prepareCountry() {
        Country country = new Country();
        country.setName("Test Country");
        return country;
    }

    static DocumentType prepareDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Test Document Type");
        return documentType;
    }

    static User prepareUser() {
        User user = new User();
        user.setLogin("Test User");
        user.setEmail("Test Email");
        user.setPassword("Test Password");
        user.setRole(Role.CLIENT);
        return user;
    }

    static Visa prepareVisa() {
        Visa visa = new Visa();
        visa.setType("Test Visa Type");
        visa.setPrice(999.);
        visa.setCountry(prepareCountry());
        visa.addDocumentType(prepareDocumentType());
        return visa;
    }

    private static Passport preparePassport() {
        Passport passport = new Passport();
        passport.setCountryOfResidence("Test Country");
        passport.setDateOfEnding(LocalDate.now());
        passport.setDateOfReceiving(LocalDate.now());
        passport.setNumber("Test Number");
        return passport;
    }

    private static VisaInfo prepareVisaInfo() {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setVisaPathOnServer("Test Visa Path");
        visaInfo.setVisa(prepareVisa());
        visaInfo.setDateOfReceiving(LocalDate.now());
        visaInfo.setDateFrom(LocalDate.now());
        visaInfo.setDateTo(LocalDate.now());
        visaInfo.setNumOfDaysResidence(5);
        visaInfo.setNameOfClient("Test Name");
        visaInfo.setSurnameOfClient("Test Surname");
        return visaInfo;
    }

    private static ClientInfo prepareClientInfo() {
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
