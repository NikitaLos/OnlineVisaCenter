package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApplicationController  {

    private ApplicationService applicationService;
    private CountryService countryService;
    private VisaService visaService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, CountryService countryService, VisaService visaService) {
        this.applicationService = applicationService;
        this.countryService = countryService;
        this.visaService = visaService;
    }

    @GetMapping(value = "/get_application/{application_id}")
    public ApplicationResponseDTO getApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        return applicationService.convertToDTO(application);//todo
    }

    @GetMapping(value = "/get_countries")
    public List<CountryDTO> getCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryService.convertToDTO(country))
                .collect(Collectors.toList());
    }


}
