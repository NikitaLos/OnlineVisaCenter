package com.vironit.onlinevisacenter.converter.country;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDTOConverter implements Converter<Country, CountryDTO> {
    @Override
    public CountryDTO convert(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        BeanUtils.copyProperties(country,countryDTO);
        return countryDTO;
    }
}
