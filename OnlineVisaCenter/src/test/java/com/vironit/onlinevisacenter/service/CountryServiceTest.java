package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryServiceTest extends BaseServiceTest {

    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryDAO countryDAO;

    @Test
    public void addCountryTest() throws ServiceException, DAOException {
        Country country = entityHelper.prepareCountry();
        countryService.addCountry(country);
        verify(countryDAO,times(1)).save(country);
        verify(countryDAO,times(1)).findCountryByName(country);
    }

    @Test
    public void updateCountryTest() throws ServiceException, DAOException {
        Country country = entityHelper.prepareCountry();
        countryService.updateCountry(country);
        verify(countryDAO,times(1)).update(country);
    }

    @Test
    public void getCountryTest() throws DAOException, ServiceException {
        Country country = entityHelper.prepareCountry();
        when(countryDAO.find(1)).thenReturn(country);
        assertEquals(country,countryService.getCountry(1));
    }

    @Test
    public void getAllCountriesTest() throws DAOException, ServiceException {
        Country[] countries = {entityHelper.prepareCountry(),entityHelper.prepareCountry()};
        when(countryDAO.findAll(Country.class)).thenReturn(Arrays.asList(countries));
        assertEquals(2,countryService.getAll().size());
    }

    @Test
    public void deleteCountryById() throws ServiceException, DAOException {
        countryService.deleteCountryById(1);
        verify(countryDAO,times(1)).deleteById(1);

    }

    @Test(expected = ServiceException.class)
    public void isDuplicate() throws ServiceException, DAOException {
        Country country = entityHelper.prepareCountry();
        List<Country> expectedList = new ArrayList<>();
        expectedList.add(country);
        when(countryDAO.findCountryByName(country)).thenReturn(expectedList);
        countryService.addCountry(country);
    }


}
