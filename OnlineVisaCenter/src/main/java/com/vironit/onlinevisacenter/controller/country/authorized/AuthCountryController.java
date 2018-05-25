package com.vironit.onlinevisacenter.controller.country.authorized;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.converter.CountryConverter;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth_user")
public class AuthCountryController {

    private CountryService countryService;
    private CountryConverter countryConverter;

    @Autowired
    public AuthCountryController(CountryService countryService,CountryConverter countryConverter) {
        this.countryService = countryService;
        this.countryConverter = countryConverter;
    }

    @GetMapping(value = "/get_countries")
    public List<CountryDTO> getCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryConverter.convertToDTO(country))
                .collect(Collectors.toList());
    }
}
