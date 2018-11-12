package com.vironit.onlinevisacenter.converter;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.request.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.request.PassportRequestDTO;
import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.ClientInfoResponseDTO;
import com.vironit.onlinevisacenter.dto.response.PassportResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaInfoResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.ClientInfo;
import com.vironit.onlinevisacenter.entity.Passport;
import com.vironit.onlinevisacenter.entity.VisaInfo;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConverter {

    private VisaConverter visaConverter;
    private UserConverter userConverter;
    private VisaService visaService;

    @Autowired
    public ApplicationConverter(VisaConverter visaConverter, UserConverter userConverter, VisaService visaService) {
        this.visaConverter = visaConverter;
        this.userConverter = userConverter;
        this.visaService = visaService;
    }

    public Application convertToEntity(ApplicationRequestDTO applicationRequestDTO) throws ConverterException {
        try {
            Application application = new Application();
            application.setId(applicationRequestDTO.getId());
            application.setComments(applicationRequestDTO.getComments());
            application.setCreationTime(applicationRequestDTO.getCreationTime());
            application.setResult(applicationRequestDTO.getResult());
            application.setStatus(applicationRequestDTO.getStatus());
            application.setVisaInfo(convertVisaInfoToEntity(applicationRequestDTO.getVisaInfo()));
            application.setClientInfo(convertClientInfoToEntity(applicationRequestDTO.getClientInfo()));
            application.setUser(applicationRequestDTO.getUser());
            return application;
        } catch (ServiceException e) {
            throw new ConverterException("Error of converting ApplicationDTO to entity",e);
        }

    }

    public ApplicationResponseDTO convertToDTO(Application application){
        ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
        applicationResponseDTO.setId(application.getId());
        applicationResponseDTO.setComments(application.getComments());
        applicationResponseDTO.setCreationTime(application.getCreationTime());
        applicationResponseDTO.setResult(application.getResult());
        applicationResponseDTO.setStatus(application.getStatus());
        applicationResponseDTO.setUser(userConverter.convertToDTO(application.getUser()));
        applicationResponseDTO.setClientInfo(convertClientInfoToDTO(application.getClientInfo()));
        applicationResponseDTO.setVisaInfo(convertVisaInfoToDTO(application.getVisaInfo()));
        return applicationResponseDTO;
    }

    private PassportResponseDTO convertPassportToDTO(Passport passport){
        PassportResponseDTO passportDTO = new PassportResponseDTO();
        passportDTO.setDateOfEnding(passport.getDateOfEnding());
        passportDTO.setDateOfReceiving(passport.getDateOfReceiving());
        passportDTO.setNumber(passport.getNumber());
        passportDTO.setCountryOfResidence(passport.getCountryOfResidence());
        passportDTO.setId(passport.getId());
        return passportDTO;
    }


    private ClientInfoResponseDTO convertClientInfoToDTO(ClientInfo clientInfo){
        ClientInfoResponseDTO clientInfoDTO = new ClientInfoResponseDTO();
        clientInfoDTO.setPassport(convertPassportToDTO(clientInfo.getPassport()));
        clientInfoDTO.setAimOfVisit(clientInfo.getAimOfVisit());
        clientInfoDTO.setDateOfBirth(clientInfo.getDateOfBirth());
        clientInfoDTO.setSex(clientInfo.getSex());
        clientInfoDTO.setPhotoPath(clientInfo.getPhotoPathOnServer());
        clientInfoDTO.setPhoneNumber(clientInfo.getPhoneNumber());
        clientInfoDTO.setName(clientInfo.getName());
        clientInfoDTO.setSurname(clientInfo.getSurname());
        clientInfoDTO.setId(clientInfo.getId());
        return clientInfoDTO;
    }

    private VisaInfoResponseDTO convertVisaInfoToDTO(VisaInfo visaInfo){
        VisaInfoResponseDTO visaInfoResponseDTO = new VisaInfoResponseDTO();
        visaInfoResponseDTO.setVisa(visaConverter.convertToDTO(visaInfo.getVisa()));
        visaInfoResponseDTO.setDateFrom(visaInfo.getDateFrom());
        visaInfoResponseDTO.setDateTo(visaInfo.getDateTo());
        visaInfoResponseDTO.setNumOfDaysResidence(visaInfo.getNumOfDaysResidence());
        visaInfoResponseDTO.setId(visaInfo.getId());
        return visaInfoResponseDTO;
    }

    private Passport convertPassportToEntity(PassportRequestDTO passportDTO){
        Passport passport = new Passport();
        passport.setId(passportDTO.getId());
        passport.setDateOfEnding(passportDTO.getDateOfEnding());
        passport.setDateOfReceiving(passportDTO.getDateOfReceiving());
        passport.setNumber(passportDTO.getNumber());
        passport.setCountryOfResidence(passportDTO.getCountryOfResidence());
        return passport;
    }

    private ClientInfo convertClientInfoToEntity(ClientInfoRequestDTO clientInfoDTO){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setId(clientInfoDTO.getId());
        clientInfo.setPassport(convertPassportToEntity(clientInfoDTO.getPassport()));
        clientInfo.setAimOfVisit(clientInfoDTO.getAimOfVisit());
        clientInfo.setDateOfBirth(clientInfoDTO.getDateOfBirth());
        clientInfo.setSex(clientInfoDTO.getSex());
        clientInfo.setPhotoPathOnServer(clientInfoDTO.getPhotoPath());
        clientInfo.setPhoneNumber(clientInfoDTO.getPhoneNumber());
        clientInfo.setName(clientInfoDTO.getName());
        clientInfo.setSurname(clientInfoDTO.getSurname());
        return clientInfo;
    }

    private VisaInfo convertVisaInfoToEntity(VisaInfoRequestDTO visaInfoDTO) throws ServiceException {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setId(visaInfoDTO.getId());
        visaInfo.setVisa(visaService.getVisa(visaInfoDTO.getVisaId()));
        visaInfo.setDateFrom(visaInfoDTO.getDateFrom());
        visaInfo.setDateTo(visaInfoDTO.getDateTo());
        visaInfo.setNumOfDaysResidence(visaInfoDTO.getNumOfDaysResidence());
        return visaInfo;

    }
}
