package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
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

public class VisaServiceTest extends BaseServiceTest {
    @Autowired
    private VisaService visaService;

    @MockBean
    private VisaDAO visaDAO;

    @Test
    public void addVisaTest() throws ServiceException, DAOException {
        Visa visa = entityHelper.prepareVisa();
        visaService.addVisa(visa);
        verify(visaDAO, times(1)).save(visa);
        verify(visaDAO, times(1)).findVisaByType(visa);
    }

    @Test
    public void updateVisaTest() throws ServiceException, DAOException {
        Visa visa = entityHelper.prepareVisa();
        visaService.updateVisa(visa);
        verify(visaDAO, times(1)).update(visa);
    }

    @Test
    public void getVisaTest() throws DAOException, ServiceException {
        Visa visa = entityHelper.prepareVisa();
        when(visaDAO.find(1)).thenReturn(visa);
        assertEquals(visa, visaService.getVisa(1));
    }

    @Test
    public void deleteVisaById() throws ServiceException, DAOException {
        visaService.deleteVisaById(1);
        verify(visaDAO, times(1)).deleteById(1);
    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        Visa[] visas = {entityHelper.prepareVisa(), entityHelper.prepareVisa()};
        when(visaDAO.findAll(Visa.class)).thenReturn(Arrays.asList(visas));
        assertEquals(2, visaService.getAll().size());
    }

    @Test
    public void getVisasByCountryTest() throws DAOException, ServiceException {
        Visa[] visas = {entityHelper.prepareVisa(),entityHelper.prepareVisa()};
        Country country = entityHelper.prepareCountry();
        when(visaDAO.findVisasByCountry(country)).thenReturn(Arrays.asList(visas));
        assertEquals(2,visaService.getVisasByCountry(country).size());
    }

    @Test(expected = ServiceException.class)
    public void isDuplicate() throws ServiceException, DAOException {
        Visa visa = entityHelper.prepareVisa();
        List<Visa> expectedList = new ArrayList<>();
        expectedList.add(visa);
        when(visaDAO.findVisaByType(visa)).thenReturn(expectedList);
        visaService.addVisa(visa);
    }
}
