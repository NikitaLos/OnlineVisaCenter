package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.AbstractTest;
import com.vironit.onlinevisacenter.converter.country.CountryDTOToCountryConverter;
import com.vironit.onlinevisacenter.converter.country.CountryToCountryDTOConverter;
import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.repository.jpa.CountryDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryServiceTest extends AbstractTest {

    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryToCountryDTOConverter toCountryDTOConverter;

    @MockBean
    private CountryDTOToCountryConverter toCountryConverter;

    @MockBean
    private CountryDAO countryDAO;

    @Mock
    private Country country;

    @Mock
    private CountryDTO countryDTO;

    @Before
    public void setUp() {
        when(toCountryDTOConverter.convert(country)).thenReturn(countryDTO);
        when(toCountryConverter.convert(countryDTO)).thenReturn(country);
        when(country.getId()).thenReturn(1);
        when(country.getName()).thenReturn("Test Country");
    }

    @Test
    public void getCountryTest() {
        when(countryDAO.findById(1)).thenReturn(Optional.of(country));
        assertEquals(countryDTO,countryService.getCountry(1));
    }

    @Test
    public void addCountryTest() {
        countryService.addCountry(countryDTO);
        verify(countryDAO, times(1)).save(country);
        verify(countryDAO, times(1)).findByName(country.getName());
    }

    @Test
    public void updateCountryTest() {
        countryService.updateCountry(countryDTO);
        verify(countryDAO, times(1)).save(country);
    }

    @Test
    public void getAllCountriesTest() {
        Country[] countries = {country, country};
        when(countryDAO.findAll()).thenReturn(Arrays.asList(countries));
        assertEquals(2, countryService.getAll().size());
    }

    @Test
    public void deleteCountryById() {
        countryService.deleteCountryById(1);
        verify(countryDAO, times(1)).deleteById(1);

    }

    @Test(expected = DuplicateException.class)
    public void isDuplicate() {
        List<Country> expectedList = new ArrayList<>();
        expectedList.add(country);
        when(countryDAO.findByName(country.getName())).thenReturn(expectedList);
        countryService.addCountry(countryDTO);
    }


}
