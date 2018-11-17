package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.repository.jpa.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private CountryDAO countryDAO;

    @Autowired
    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public void addCountry(Country country) throws ServiceException {
        checkDuplicate(country);
        countryDAO.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.save(country);
    }

    @Override
    public Country getCountry(Integer id) {
        return countryDAO.findById(id).orElseThrow(SecurityException::new);
    }

    @Override
    public List<Country> getAll() {
        return countryDAO.findAll();
    }

    @Override
    public void deleteCountryById(Integer countryId) {
        countryDAO.deleteById(countryId);

    }

    private void checkDuplicate(Country country) throws ServiceException {
        if (!countryDAO.findByName(country.getName()).isEmpty()) {
            throw new ServiceException("Such a country already exists");
        }
    }

}
