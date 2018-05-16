package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CountryService {
    @Transactional
    void addCountry(Country country) throws CountryServiceException, DuplicateException;
    @Transactional
    void updateCountry(Country country) throws CountryServiceException;
    @Transactional
    void deleteCountryById(Integer id) throws UserServiceException;
    Country getCountry(Integer id) throws CountryServiceException;
    List<Country> getAll() throws CountryServiceException;
    Country convertToEntity(CountryDTO countryDTO);
    CountryDTO convertToDTO(Country country);
}
