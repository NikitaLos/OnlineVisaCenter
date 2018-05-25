package com.vironit.onlinevisacenter.controller.application.client;

import com.vironit.onlinevisacenter.dto.converter.ApplicationConverter;
import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.validation.ValidationSequence;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientApplicationController {

    private ApplicationService applicationService;
    private UserService userService;
    private ApplicationConverter applicationConverter;

    @Autowired
    public ClientApplicationController(ApplicationService applicationService, UserService userService,
                                       ApplicationConverter applicationConverter) {
        this.applicationService = applicationService;
        this.userService= userService;
        this.applicationConverter = applicationConverter;
    }


    @PostMapping(value = "/add_application")
    public void addApplication(@Validated(ValidationSequence.class) @RequestBody ApplicationRequestDTO applicationDTO, Authentication authentication) throws ApplicationServiceException, UserServiceException, ConverterException {
        User user = userService.getUserByLogin(authentication.getName());
        applicationDTO.setUser(user);
        Application application = applicationConverter.convertToEntity(applicationDTO);
        applicationService.addApplicationToQueue(application);
    }

    @GetMapping(value = "/get_applications_by_user")
    public List<ApplicationResponseDTO> viewApplicationsByClient(Authentication authentication) throws ApplicationServiceException, UserServiceException {
        User user = userService.getUserByLogin(authentication.getName());
        List<Application> applications = applicationService.getUserApplications(user.getId());
        return applications.stream()
                .map(application -> applicationConverter.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/update_application")
    public void updateApplication(@Validated(ValidationSequence.class) @RequestBody ApplicationRequestDTO applicationDTO, Authentication authentication) throws ApplicationServiceException, UserServiceException, ConverterException {
        User user = userService.getUserByLogin(authentication.getName());
        applicationDTO.setUser(user);
        Application application = applicationConverter.convertToEntity(applicationDTO);
        applicationService.updateApplication(application);
    }

    @DeleteMapping(value = "/delete_application/{application_id}")
    public void deleteApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        applicationService.deleteApplicationFromQueue(application);
    }
}
