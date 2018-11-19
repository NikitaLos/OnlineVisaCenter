package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;

import java.util.List;

public interface CountryService {
    void addCountry(CountryDTO countryDTO);

    void updateCountry(CountryDTO countryDTO);

    void deleteCountryById(Integer id);

    CountryDTO getCountry(Integer id);

    List<CountryDTO> getAll();
}
