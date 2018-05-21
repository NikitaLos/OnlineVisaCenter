package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth_user")
public class ClientEmployeeController {

    private ApplicationService applicationService;
    private CountryService countryService;

    @Autowired
    public ClientEmployeeController(ApplicationService applicationService, CountryService countryService) {
        this.applicationService = applicationService;
        this.countryService = countryService;
    }

    @GetMapping(value = "/get_application/{application_id}")
    public ApplicationResponseDTO getApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        return applicationService.convertToDTO(application);
    }

    @GetMapping(value = "/get_countries")
    public List<CountryDTO> getCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryService.convertToDTO(country))
                .collect(Collectors.toList());
    }


}
