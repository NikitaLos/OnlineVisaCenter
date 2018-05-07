package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.ServerLogger;
import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.ClientInfoDTO;
import com.vironit.onlinevisacenter.dto.PassportDTO;
import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.response.*;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.EmbassyService;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDAO applicationDAO;
    private VisaService visaService;
    private UserService userService;

    private ServerLogger logger = new ServerLogger(ApplicationServiceImpl.class);

    @Autowired
    public ApplicationServiceImpl(ApplicationDAO applicationDAO, VisaService visaService, UserService userService) {
        this.applicationDAO = applicationDAO;
        this.visaService = visaService;
        this.userService = userService;
    }

    @Override
    public void addApplicationToQueue(Application application) throws ApplicationServiceException {
//        validateApplication(application);
//        verifyCheck(application);
        try {
            application.setCreationTime(LocalDateTime.now());
            application.setStatus(Status.IN_VC_QUEUE);
            application.setResult(Result.NO_RESULT);
            applicationDAO.save(application);
            logger.info("Application was add to queue");
        } catch (EntitySaveException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void updateApplication(Application application) throws ApplicationServiceException {
//        validateApplication(application);
        try {
            applicationDAO.update(application);
        } catch (EntityUpdateException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void deleteApplicationFromQueue(Application application) throws ApplicationServiceException {
        try {
            applicationDAO.delete(application);
        } catch (EntityDeleteException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public List<Application> getApplications() throws ApplicationServiceException {
        try {
            return  applicationDAO.findAll(Application.class);
        } catch (EntityFindException e) {
            throw new ApplicationServiceException(e);

        }
    }

    @Override
    public Application getApplication(Integer id) throws ApplicationServiceException {
        try {
            return applicationDAO.find(id);
        } catch (EntityFindException e) {
            throw  new ApplicationServiceException(e);
        }
    }


    @Override
    public void approveApplication(Application application) throws ApplicationServiceException {
        changeApplicationStatus(application,Status.IN_VC_QUEUE);
//        updateApplication(application);
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
    public List<Application> getUserApplications(Integer userId) throws ApplicationServiceException {
        try {
            return applicationDAO.findApplicationsByClient(userId);
        } catch (EntityFindException e) {
            throw new ApplicationServiceException(e);
        }
    }

    private void validateApplication(Application application) {
//        validateClientInfo(application.getClientInfo());
//        validateVisaInfo(application.getVisaInfo());
    }


//    private void validateClientInfo(ClientInfo clientInfo){
//        LocalDate dateOfBirth = clientInfo.getDateOfBirth();
////        List<ClientDocument> documents = clientInfo.getClientDocuments();//todo
//        String name = clientInfo.getName();
//        String surname = clientInfo.getSurname();
//        String phoneNumber = clientInfo.getPhoneNumber();
//        String photo = clientInfo.getPhotoPathOnServer();
//        String sex = clientInfo.getSex();
//        AimOfVisit aimOfVisit = clientInfo.getAimOfVisit();
//        Passport passport = clientInfo.getPassport();
//        String country = passport.getCountryOfResidence();
//        LocalDate dateOfEnding = passport.getDateOfEnding();
//        LocalDate dateOfReceiving = passport.getDateOfReceiving();
//        String passportNumber = passport.getNumber();
//
//        if(documents==null|dateOfBirth==null|name==null|surname==null|phoneNumber==null|photo==null|sex==null|aimOfVisit==null |
//                country==null| dateOfEnding==null|dateOfReceiving==null|passportNumber==null){
//            //todo exception
//
//        }else {
//            if(documents.size()!=clientInfo.getApplication().getVisaInfo().getVisa().getRequiredDocumentTypes().size()){
//                //todo exception
//            }
//            if (YEARS.between(dateOfBirth,LocalDate.now())<18){
//                //todo exception
//            }
//            if(dateOfEnding.isBefore(dateOfReceiving)){
//                //todo exception
//            }
//        }
//
//    }

//    private void validateVisaInfo(VisaInfo visaInfo){
////        Passport passport = visaInfo.getApplication().getClientInfo().getPassport();
//        long durationOfPassport = DAYS.between(passport.getDateOfReceiving(),passport.getDateOfEnding());
//        LocalDate dateFrom = visaInfo.getDateFrom();
//        LocalDate dateTo = visaInfo.getDateTo();
//        Integer daysOfResidence =  visaInfo.getNumOfDaysResidence();
//
//        if (dateTo==null|dateFrom==null|daysOfResidence==null){
//            //todo exception
//        }else {
//            if(dateTo.isBefore(dateFrom)||DAYS.between(dateFrom,dateTo)>daysOfResidence||durationOfPassport<daysOfResidence){
//                //todo exception
//            }
//        }
//    }

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

    @Override
    public Application convertToEntity(ApplicationRequestDTO applicationRequestDTO) throws VisaServiceException, UserServiceException {
        Application application = new Application();
        application.setId(applicationRequestDTO.getId());
        application.setComments(applicationRequestDTO.getComments());
        application.setCreationTime(applicationRequestDTO.getCreationTime());
        application.setResult(applicationRequestDTO.getResult());
        application.setStatus(applicationRequestDTO.getStatus());
        application.setUser(userService.getUser(applicationRequestDTO.getUserId()));
        application.setClientInfo(convertClientInfoToEntity(applicationRequestDTO.getClientInfo()));
        application.setVisaInfo(convertVisaInfoToEntity(applicationRequestDTO.getVisaInfo()));
        return application;
    }

    @Override
    public ApplicationResponseDTO convertToDTO(Application application){
        ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
        applicationResponseDTO.setId(application.getId());
        applicationResponseDTO.setComments(application.getComments());
        applicationResponseDTO.setCreationTime(application.getCreationTime());
        applicationResponseDTO.setResult(application.getResult());
        applicationResponseDTO.setStatus(application.getStatus());
        applicationResponseDTO.setUser(userService.convertToDTO(application.getUser()));
        applicationResponseDTO.setClientInfo(convertClientInfoToDTO(application.getClientInfo()));
        applicationResponseDTO.setVisaInfo(convertVisaInfoToDTO(application.getVisaInfo()));
        return applicationResponseDTO;
    }

    private PassportDTO convertPassportToDTO(Passport passport){
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setDateOfEnding(passport.getDateOfEnding());
        passportDTO.setDateOfReceiving(passport.getDateOfReceiving());
        passportDTO.setNumber(passport.getNumber());
        passportDTO.setCountryOfResidence(passport.getCountryOfResidence());
        passportDTO.setId(passport.getId());
        return passportDTO;
    }

    private ClientInfoDTO convertClientInfoToDTO(ClientInfo clientInfo){
        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        clientInfoDTO.setPassport(convertPassportToDTO(clientInfo.getPassport()));
        clientInfoDTO.setAimOfVisit(clientInfo.getAimOfVisit());
        clientInfoDTO.setDateOfBirth(clientInfo.getDateOfBirth());
        clientInfoDTO.setSex(clientInfo.getSex());
        clientInfoDTO.setPhoneNumber(clientInfo.getPhoneNumber());
        clientInfoDTO.setName(clientInfo.getName());
        clientInfoDTO.setSurname(clientInfo.getSurname());
        clientInfoDTO.setId(clientInfo.getId());
        return clientInfoDTO;
    }

    private VisaInfoResponseDTO convertVisaInfoToDTO(VisaInfo visaInfo){
        VisaInfoResponseDTO visaInfoResponseDTO = new VisaInfoResponseDTO();
        visaInfoResponseDTO.setVisa(visaService.convertToDTO(visaInfo.getVisa()));
        visaInfoResponseDTO.setDateFrom(visaInfo.getDateFrom());
        visaInfoResponseDTO.setDateTo(visaInfo.getDateTo());
        visaInfoResponseDTO.setNumOfDaysResidence(visaInfo.getNumOfDaysResidence());
        visaInfoResponseDTO.setId(visaInfo.getId());
        return visaInfoResponseDTO;
    }

    private Passport convertPassportToEntity(PassportDTO passportDTO){
        Passport passport = new Passport();
        passport.setId(passportDTO.getId());
        passport.setDateOfEnding(passportDTO.getDateOfEnding());
        passport.setDateOfReceiving(passportDTO.getDateOfReceiving());
        passport.setNumber(passportDTO.getNumber());
        passport.setCountryOfResidence(passportDTO.getCountryOfResidence());
        return passport;
    }

    private ClientInfo convertClientInfoToEntity(ClientInfoDTO clientInfoDTO){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setId(clientInfoDTO.getId());
        clientInfo.setPassport(convertPassportToEntity(clientInfoDTO.getPassport()));
        clientInfo.setAimOfVisit(clientInfoDTO.getAimOfVisit());
        clientInfo.setDateOfBirth(clientInfoDTO.getDateOfBirth());
        clientInfo.setSex(clientInfoDTO.getSex());
        clientInfo.setPhoneNumber(clientInfoDTO.getPhoneNumber());
        clientInfo.setName(clientInfoDTO.getName());
        clientInfo.setSurname(clientInfoDTO.getSurname());
        return clientInfo;
    }

    private VisaInfo convertVisaInfoToEntity(VisaInfoRequestDTO visaInfoDTO) throws VisaServiceException {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setId(visaInfoDTO.getId());
        visaInfo.setVisa(visaService.getVisa(visaInfoDTO.getVisaId()));
        visaInfo.setDateFrom(visaInfoDTO.getDateFrom());
        visaInfo.setDateTo(visaInfoDTO.getDateTo());
        visaInfo.setNumOfDaysResidence(visaInfoDTO.getNumOfDaysResidence());
        return visaInfo;
    }
}
