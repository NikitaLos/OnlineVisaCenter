package com.vironit.onlinevisacenter.converter;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    public Country convertToEntity(CountryDTO countryDTO){
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setId(countryDTO.getId());
        return country;
    }

    public CountryDTO convertToDTO(Country country){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(country.getName());
        countryDTO.setId(country.getId());
        return countryDTO;
    }
}
