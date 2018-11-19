package com.vironit.onlinevisacenter.converter.country;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryDTOToCountryConverter implements Converter<CountryDTO, Country> {
    @Override
    public Country convert(CountryDTO countryDTO) {
        Country country = new Country();
        BeanUtils.copyProperties(countryDTO,country);
        return country;
    }
}
