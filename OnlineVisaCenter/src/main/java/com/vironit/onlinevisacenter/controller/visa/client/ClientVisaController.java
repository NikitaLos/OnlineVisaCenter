package com.vironit.onlinevisacenter.controller.visa.client;

import com.vironit.onlinevisacenter.dto.converter.CountryConverter;
import com.vironit.onlinevisacenter.dto.converter.VisaConverter;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientVisaController {

    private CountryService countryService;
    private VisaService visaService;
    private VisaConverter visaConverter;

    @Autowired
    public ClientVisaController(CountryService countryService, VisaService visaService,
                                VisaConverter visaConverter) {
        this.countryService = countryService;
        this.visaService = visaService;
        this.visaConverter = visaConverter;
    }

    @GetMapping(value = "/get_visas_by_country/{country_id}")
    public List<VisaResponseDTO> getVisasByCountry(@PathVariable("country_id") Integer id) throws VisaServiceException, CountryServiceException {
        Country country = countryService.getCountry(id);
        List<Visa> visas = visaService.getVisasByCountry(country);
        return visas.stream()
                .map(visa -> visaConverter.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/get_visa/{visa_id}")
    public VisaResponseDTO getVisasById(@PathVariable("visa_id") Integer id) throws VisaServiceException {
        Visa visa =  visaService.getVisa(id);
        return visaConverter.convertToDTO(visa);
    }

    @GetMapping(value = "/get_aims_of_visit")
    public AimOfVisit[] getAimsOfVisit() {
        return AimOfVisit.values();
    }
}
