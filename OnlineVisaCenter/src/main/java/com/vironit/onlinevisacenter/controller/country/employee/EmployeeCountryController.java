package com.vironit.onlinevisacenter.controller.country.employee;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.converter.CountryConverter;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeCountryController {

    private CountryService countryService;
    private CountryConverter countryConverter;

    @Autowired
    public EmployeeCountryController( CountryService countryService, CountryConverter countryConverter) {
        this.countryService = countryService;
        this.countryConverter = countryConverter;
    }

    @GetMapping(value = "/get_countries")
    public List<CountryDTO> getCountries() throws ServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryConverter.convertToDTO(country))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_country")
    public void addCountry(@Valid @RequestBody CountryDTO countryDTO) throws ServiceException {
        Country country = countryConverter.convertToEntity(countryDTO);
        countryService.addCountry(country);
    }

    @PostMapping(value = "/change_country")
    public void changeCountry(@Valid @RequestBody CountryDTO countryDTO) throws ServiceException {
        Country country = countryConverter.convertToEntity(countryDTO);
        countryService.updateCountry(country);
    }

    @DeleteMapping(value = "/delete_country/{country_id}")
    public void deleteCountry(@PathVariable("country_id") Integer countryId) throws ServiceException {
        countryService.deleteCountryById(countryId);
    }
}
