package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDAO countryDAO;

    @Autowired
    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public void addCountry(Country country) throws ServiceException {
        try{
            checkDuplicate(country);
            countryDAO.save(country);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCountry(Country country) throws ServiceException {
        try {
            countryDAO.update(country);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Country getCountry(Integer id) throws ServiceException {
        try {
            return countryDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Country> getAll() throws ServiceException {
        try {
            return countryDAO.findAll(Country.class);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCountryById(Integer countryId) throws ServiceException {
        try {
            countryDAO.deleteById(countryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void checkDuplicate(Country country) throws ServiceException, DAOException {
        if (!countryDAO.findCountryByName(country).isEmpty()){
            throw new ServiceException("Such a country already exists");
        }
    }

}
