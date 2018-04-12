package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.ServerLogger;
import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.EmbassyService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.time.temporal.ChronoUnit.YEARS;


public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDAO applicationDAO;

    private ServerLogger logger = new ServerLogger(ApplicationServiceImpl.class);

    public ApplicationServiceImpl(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public void addApplicationToQueue(Application application) throws ApplicationServiceException {
        validateApplication(application);
        verifyCheck(application);
        try {
            applicationDAO.save(application);
            logger.info("Application was add to queue");
        } catch (EntitySaveException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void updateApplication(Application application) {
        validateApplication(application);
//        applicationDAO.update(application);
    }

    @Override
    public void deleteApplicationFromQueue(Application application) {
//        applicationDAO.delete(application);
    }

    @Override
    public Queue<Application> getApplicationQueue() {
//        return new LinkedList<>(applicationDAO.findAll(Application.class));
        return null;
    }

    @Override
    public Application getApplication(Application application) {
//        return applicationDAO.find(application.getId());
        return null;
    }


    @Override
    public void approveApplication(Application application) {
        changeApplicationStatus(application,Status.IN_VC_QUEUE);
        updateApplication(application);
    }

    @Override
    public void denyApplication(Application application, String comments) {
        changeApplicationStatus(application,Status.REVIEWED);
        addCommentsToApplication(application,comments);
//        applicationDAO.update(application);
    }

    @Override
    public void transferApplicationToEmbassy(Application application, EmbassyService embassyService) {
        embassyService.addApplicationToQueue(application);
//        applicationDAO.update(application);
    }

    @Override
    public List<Application> getUserApplications(User user) {
//        return applicationDAO.findApplicationsByClient(user);
        return null;
    }

    private void validateApplication(Application application) {
        validateClientInfo(application.getClientInfo());
        validateVisaInfo(application.getVisaInfo());
    }


    private void validateClientInfo(ClientInfo clientInfo){
        LocalDate dateOfBirth = clientInfo.getDateOfBirth();
        String name = clientInfo.getName();
        String surname = clientInfo.getSurname();
        String phoneNumber = clientInfo.getPhoneNumber();
        String photo = clientInfo.getPhotoPathOnServer();
        String sex = clientInfo.getSex();
        AimOfVisit aimOfVisit = clientInfo.getAimOfVisit();
        Passport passport = clientInfo.getPassport();
        String country = passport.getCountryOfResidence();
        LocalDate dateOfEnding = passport.getDateOfEnding();
        LocalDate dateOfReceiving = passport.getDateOfReceiving();
        String passportNumber = passport.getNumber();

        if(dateOfBirth==null|name==null|surname==null|phoneNumber==null|photo==null|sex==null|aimOfVisit==null |
                country==null| dateOfEnding==null|dateOfReceiving==null|passportNumber==null){
            //todo exception

        }else {
            if (YEARS.between(dateOfBirth,LocalDate.now())<18){
                //todo exception
            }
            if(dateOfEnding.isBefore(dateOfReceiving)){
                //todo exception
            }
        }

    }

    private void validateVisaInfo(VisaInfo visaInfo){
//        Passport passport = visaInfo.getApplication().getClientInfo().getPassport();
//        long durationOfPassport = DAYS.between(passport.getDateOfReceiving(),passport.getDateOfEnding());
//        LocalDate dateFrom = visaInfo.getDateFrom();
//        LocalDate dateTo = visaInfo.getDateTo();
//        Integer daysOfResidence =  visaInfo.getNumOfDaysResidence();
//        List<ClientDocument> documents = visaInfo.getClientDocuments();
//        Visa visa = visaInfo.getVisa();
//
//        if (documents==null|dateTo==null|dateFrom==null|daysOfResidence==null){
//            //todo exception
//        }else {
//            if(documents.size()!=visa.getRequiredDocumentTypes().size()){
//                //todo exception TODO
//            }
//            if(dateTo.isBefore(dateFrom)||DAYS.between(dateFrom,dateTo)>daysOfResidence||durationOfPassport<daysOfResidence){
//                //todo exception
//            }
//        }
    }

    private void verifyCheck(Application application) {
        if (application.getCheck()!=null){
            changeApplicationStatus(application,Status.IN_VC_QUEUE);

        }else {
            changeApplicationStatus(application,Status.IN_EM_QUEUE);
            //todo exception
        }
//        applicationDAO.update(application);
    }


    private void addCommentsToApplication(Application application, String comments) {
        application.setComments(comments);
//        applicationDAO.update(application);
    }

}
