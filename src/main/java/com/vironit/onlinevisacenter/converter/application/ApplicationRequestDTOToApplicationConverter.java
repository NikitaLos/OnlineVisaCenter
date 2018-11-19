package com.vironit.onlinevisacenter.converter.application;

import com.vironit.onlinevisacenter.converter.clientinfo.ClientInfoRequestDTOToClientInfoConverter;
import com.vironit.onlinevisacenter.converter.user.UserDTOToUserConverter;
import com.vironit.onlinevisacenter.converter.visainfo.VisaInfoRequestDTOToVisaInfoConverter;
import com.vironit.onlinevisacenter.dto.application.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.entity.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRequestDTOToApplicationConverter implements Converter<ApplicationRequestDTO, Application> {

    @Autowired
    private VisaInfoRequestDTOToVisaInfoConverter toVisaInfoConverter;

    @Autowired
    private ClientInfoRequestDTOToClientInfoConverter toClientInfoConverter;

    @Autowired
    private UserDTOToUserConverter toUserConverter;

    @Override
    public Application convert(ApplicationRequestDTO applicationRequestDTO) {
        Application application = new Application();
        BeanUtils.copyProperties(applicationRequestDTO,application);
        application.setVisaInfo(toVisaInfoConverter.convert(applicationRequestDTO.getVisaInfo()));
        application.setClientInfo(toClientInfoConverter.convert(applicationRequestDTO.getClientInfo()));
        application.setUser(toUserConverter.convert(applicationRequestDTO.getUserDTO()));
        return application;
    }
}
