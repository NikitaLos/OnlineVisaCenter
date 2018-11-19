package com.vironit.onlinevisacenter.converter.application;

import com.vironit.onlinevisacenter.converter.clientinfo.ClientInfoToClientInfoResponseDTO;
import com.vironit.onlinevisacenter.converter.user.UserToUserDTOConverter;
import com.vironit.onlinevisacenter.converter.visainfo.VisaInfoToVisaInfoResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToApplicationResponseDTOConverter implements Converter<Application, ApplicationResponseDTO> {

    @Autowired
    private UserToUserDTOConverter toUserDTOConverter;

    @Autowired
    private ClientInfoToClientInfoResponseDTO toClientInfoResponseDTO;

    @Autowired
    private VisaInfoToVisaInfoResponseDTOConverter toVisaInfoResponseDTOConverter;

    @Override
    public ApplicationResponseDTO convert(Application application) {
        ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
        BeanUtils.copyProperties(application,applicationResponseDTO);
        applicationResponseDTO.setUser(toUserDTOConverter.convert(application.getUser()));
        applicationResponseDTO.setClientInfo(toClientInfoResponseDTO.convert(application.getClientInfo()));
        applicationResponseDTO.setVisaInfo(toVisaInfoResponseDTOConverter.convert(application.getVisaInfo()));
        return applicationResponseDTO;
    }
}
