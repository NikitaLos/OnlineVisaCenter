package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;

public class CountryServiceImpl implements CountryService {

    CountryDAO countryDAO;

    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public void addCountry(Country country) {
        if (!countryDAO.isDuplicate(country)){
            countryDAO.create(country);
        }else {
            //todo exception
        }
    }

    @Override
    public void deleteCountry(Country country) {
        countryDAO.delete(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.update(country);
    }

    @Override
    public void getCountry(Country country) {
        countryDAO.getByPK(country.getId());
    }
}
