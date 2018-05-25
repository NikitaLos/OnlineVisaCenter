package com.vironit.onlinevisacenter.controller.application.authorized;

import com.vironit.onlinevisacenter.dto.converter.ApplicationConverter;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth_user")
public class AuthUserApplicationController {

    private ApplicationService applicationService;
    private ApplicationConverter applicationConverter;

    @Autowired
    public AuthUserApplicationController(ApplicationService applicationService, ApplicationConverter applicationConverter) {
        this.applicationService = applicationService;
        this.applicationConverter = applicationConverter;
    }

    @GetMapping(value = "/get_application/{application_id}")
    public ApplicationResponseDTO getApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        return applicationConverter.convertToDTO(application);
    }
}
