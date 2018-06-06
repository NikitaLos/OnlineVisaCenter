package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

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
    public void addCountryTest() throws CountryServiceException, DuplicateException, EntitySaveException, EntityFindException {
        Country country = entityHelper.prepareCountry();
        countryService.addCountry(country);
        verify(countryDAO,times(1)).save(country);
        verify(countryDAO,times(1)).checkDuplicate(country);
    }

    @Test
    public void updateCountryTest() throws CountryServiceException, EntityUpdateException {
        Country country = entityHelper.prepareCountry();
        countryService.updateCountry(country);
        verify(countryDAO,times(1)).update(country);
    }

    @Test
    public void getCountryTest() throws CountryServiceException, EntityFindException {
        Country country = entityHelper.prepareCountry();
        when(countryDAO.find(1)).thenReturn(country);
        assertEquals(country,countryService.getCountry(1));
    }

    @Test
    public void getAllCountriesTest() throws CountryServiceException, EntityFindException {
        Country[] countries = {entityHelper.prepareCountry(),entityHelper.prepareCountry()};
        when(countryDAO.findAll(Country.class)).thenReturn(Arrays.asList(countries));
        assertEquals(2,countryService.getAll().size());
    }

    @Test
    public void deleteCountryById() throws CountryServiceException, EntityDeleteException {
        countryService.deleteCountryById(1);
        verify(countryDAO,times(1)).deleteById(1);

    }


}
