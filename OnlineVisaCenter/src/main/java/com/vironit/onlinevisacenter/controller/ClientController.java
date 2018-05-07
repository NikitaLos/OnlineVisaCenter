package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @RequestMapping(value = "/view_countries",method = RequestMethod.GET)
    public List<CountryDTO> showCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryService.convertToDTO(country))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/visas_by_country_/{country_id}",method = RequestMethod.GET)
    public List<VisaResponseDTO> getVisasByCountry(@PathVariable("country_id") Integer id) throws VisaServiceException, CountryServiceException {
        Country country = countryService.getCountry(id);
        List<Visa> visas = visaService.getVisasByCountry(country);
        return visas.stream()
                .map(visa -> visaService.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add_application", method = RequestMethod.POST)
    public Message composeApplication(@RequestBody ApplicationRequestDTO applicationDTO) throws ApplicationServiceException, VisaServiceException, UserServiceException {
        Application application = applicationService.convertToEntity(applicationDTO);
        applicationService.addApplicationToQueue(application);
        return new Message("success");
    }

    @RequestMapping(value = "/view_applications_by_user/{user_id}", method = RequestMethod.GET)
    public List<ApplicationResponseDTO> viewApplicationsByClient(@PathVariable("user_id") Integer userId) throws ApplicationServiceException {
        List<Application> applications = applicationService.getUserApplications(userId);
        return applications.stream()
                .map(application -> applicationService.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/change_application", method = RequestMethod.POST)
    public Message changeApplication(@RequestBody ApplicationRequestDTO applicationDTO) throws ApplicationServiceException, VisaServiceException, UserServiceException {
        Application application = applicationService.convertToEntity(applicationDTO);
        applicationService.updateApplication(application);
        return new Message("success");
    }

}
