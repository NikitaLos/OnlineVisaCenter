package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private CountryService countryService;
    private ApplicationService applicationService;
    private VisaService visaService;

    @Autowired
    public ClientController(CountryService countryService, ApplicationService applicationService, VisaService visaService) {
        this.countryService = countryService;
        this.applicationService = applicationService;
        this.visaService = visaService;
    }

    @GetMapping(value = "/get_aims_of_visit")
    public AimOfVisit[] getAimsOfVisit() {
        return AimOfVisit.values();
    }


    @GetMapping(value = "/get_visas_by_country/{country_id}")
    public List<VisaResponseDTO> getVisasByCountry(@PathVariable("country_id") Integer id) throws VisaServiceException, CountryServiceException {
        Country country = countryService.getCountry(id);
        List<Visa> visas = visaService.getVisasByCountry(country);
        return visas.stream()
                .map(visa -> visaService.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_application")
    public void composeApplication(@RequestBody ApplicationRequestDTO applicationDTO,HttpSession session) throws ApplicationServiceException, VisaServiceException, UserServiceException {
        applicationDTO.setUserId((Integer) session.getAttribute("user_id"));
        Application application = applicationService.convertToEntity(applicationDTO);
        applicationService.addApplicationToQueue(application);
    }

    @GetMapping(value = "/get_applications_by_user")
    public List<ApplicationResponseDTO> viewApplicationsByClient(HttpSession session) throws ApplicationServiceException {
        List<Application> applications = applicationService.getUserApplications((Integer) session.getAttribute("user_id"));
        return applications.stream()
                .map(application -> applicationService.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/update_application")
    public void updateApplication(@RequestBody ApplicationRequestDTO applicationDTO,HttpSession session) throws ApplicationServiceException, VisaServiceException, UserServiceException {
        applicationDTO.setUserId((Integer) session.getAttribute("user_id"));
        Application application = applicationService.convertToEntity(applicationDTO);
        applicationService.updateApplication(application);
    }

    @DeleteMapping(value = "/delete_application/{application_id}")
    public void deleteApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        applicationService.deleteApplicationFromQueue(application);
    }

}
