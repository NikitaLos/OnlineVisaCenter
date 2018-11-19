package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.AbstractTest;
import com.vironit.onlinevisacenter.converter.visa.VisaRequestDTOToVisaConverter;
import com.vironit.onlinevisacenter.converter.visa.VisaToVisaResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.repository.jpa.VisaDAO;
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

public class VisaServiceTest extends AbstractTest {
    @Autowired
    private VisaService visaService;

    @MockBean
    private VisaDAO visaDAO;

    @Mock
    private VisaRequestDTO visaRequestDTO;

    @Mock
    private VisaResponseDTO visaResponseDTO;

    @MockBean
    private VisaRequestDTOToVisaConverter toVisaConverter;

    @MockBean
    private VisaToVisaResponseDTOConverter toVisaResponseDTOConverter;

    @Mock
    private Visa visa;

    @Mock
    private Country country;

    @Before
    public void setUp() {
        when(toVisaConverter.convert(visaRequestDTO)).thenReturn(visa);
        when(toVisaResponseDTOConverter.convert(visa)).thenReturn(visaResponseDTO);
        when(visa.getType()).thenReturn("type");
        when(visa.getCountry()).thenReturn(country);
        when(country.getId()).thenReturn(1);
    }

    @Test
    public void getCountryTest() {
        when(visaDAO.findById(1)).thenReturn(Optional.of(visa));
        assertEquals(visaResponseDTO,visaService.getVisa(1));
    }

    @Test
    public void addVisaTest() {
        visaService.addVisa(visaRequestDTO);
        verify(visaDAO, times(1)).save(visa);
        verify(visaDAO, times(1)).findByTypeAndCountryId(visa.getType(), visa.getCountry().getId());
    }

    @Test
    public void updateVisaTest() {
        visaService.updateVisa(visaRequestDTO);
        verify(visaDAO, times(1)).save(visa);
    }


    @Test
    public void deleteVisaById() {
        visaService.deleteVisaById(1);
        verify(visaDAO, times(1)).deleteById(1);
    }

    @Test
    public void getAllTest() {
        Visa[] visas = {visa, visa};
        when(visaDAO.findAll()).thenReturn(Arrays.asList(visas));
        assertEquals(2, visaService.getAll().size());
    }

    @Test
    public void getVisasByCountryTest() {
        Visa[] visas = {visa, visa};
        when(visaDAO.findByCountryId(country.getId())).thenReturn(Arrays.asList(visas));
        assertEquals(2, visaService.getVisasByCountryId(country.getId()).size());
    }

    @Test(expected = DuplicateException.class)
    public void isDuplicate() {
        List<Visa> expectedList = new ArrayList<>();
        expectedList.add(visa);
        when(visaDAO.findByTypeAndCountryId(visa.getType(), visa.getCountry().getId())).thenReturn(expectedList);
        visaService.addVisa(visaRequestDTO);
    }
}
