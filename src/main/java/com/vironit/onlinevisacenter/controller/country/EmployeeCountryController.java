package com.vironit.onlinevisacenter.controller.country;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee/countries")
public class EmployeeCountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<CountryDTO> getCountries() {
        return countryService.getAll();
    }

    @PostMapping
    public void addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        countryService.addCountry(countryDTO);
    }

    @PatchMapping
    public void changeCountry(@Valid @RequestBody CountryDTO countryDTO) {
        countryService.updateCountry(countryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCountry(@PathVariable("id") Integer id) {
        countryService.deleteCountryById(id);
    }
}
