package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;

import java.util.List;

public interface CountryService {
    void addCountry(Country country) throws CountryServiceException, DuplicateException;
    void deleteCountry(Country country) throws CountryServiceException;
    void updateCountry(Country country) throws CountryServiceException;
    Country getCountry(Integer id) throws CountryServiceException;

    List<Country> getAll() throws CountryServiceException;

    void deleteCountryById(Integer id) throws UserServiceException;

    Country getCountryEager(Integer id) throws CountryServiceException;
}
