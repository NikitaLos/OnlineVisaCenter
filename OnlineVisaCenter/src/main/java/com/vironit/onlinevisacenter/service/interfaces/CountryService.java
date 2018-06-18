package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CountryService {
    @Transactional
    void addCountry(Country country) throws ServiceException;
    @Transactional
    void updateCountry(Country country) throws ServiceException;
    @Transactional
    void deleteCountryById(Integer id) throws  ServiceException;
    Country getCountry(Integer id) throws ServiceException;
    List<Country> getAll() throws ServiceException;
}
