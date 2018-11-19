package com.vironit.onlinevisacenter.controller.application;

import com.vironit.onlinevisacenter.dto.application.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.service.ApplicationService;
import com.vironit.onlinevisacenter.service.UserService;
import com.vironit.onlinevisacenter.validation.ValidationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/applications")
public class ClientApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public void addApplication(@Validated(ValidationSequence.class) @RequestBody ApplicationRequestDTO applicationDTO, Authentication authentication) {
        UserDTO user = userService.getUserByLogin(authentication.getName());
        applicationDTO.setUserDTO(user);
        applicationService.addApplicationToQueue(applicationDTO);
    }

    @GetMapping
    public List<ApplicationResponseDTO> viewApplicationsByClient(Authentication authentication) {
        UserDTO user = userService.getUserByLogin(authentication.getName());
        return applicationService.getUserApplications(user.getId());
    }

    @PatchMapping
    public void updateApplication(@Validated(ValidationSequence.class) @RequestBody ApplicationRequestDTO applicationDTO, Authentication authentication) {
        UserDTO user = userService.getUserByLogin(authentication.getName());
        applicationDTO.setUserDTO(user);
        applicationService.updateApplication(applicationDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteApplication(@PathVariable("id") Integer applicationId) {
        applicationService.deleteApplicationFromQueue(applicationId);
    }
}
