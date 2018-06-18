package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class VisaDAOTest extends BaseDAOTest{

    @Autowired
    private VisaDAO visaDAO;

    private Visa testVisa;

    @Before
    public void insertCountry(){
        testVisa = entityHelper.prepareVisa();
        entityManager.persist(testVisa);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("DELETE from Visa").executeUpdate();
        entityManager.createQuery("DELETE from Country").executeUpdate();
        entityManager.createQuery("DELETE from DocumentType").executeUpdate();
    }


    @Test
    public void saveTest() throws DAOException {
        Visa visaExpected = entityHelper.prepareVisa();
        visaDAO.save(visaExpected);
        Visa visaActual = entityManager.find(Visa.class,visaExpected.getId());
        assertEquals(visaExpected,visaActual);
    }

    @Test
    public void updateTest() throws DAOException {
        testVisa.setType("New Test Visa");
        visaDAO.update(testVisa);
        testVisa = visaDAO.find(testVisa.getId());
        assertEquals("New Test Visa",testVisa.getType());
    }

    @Test
    public void findTest() throws DAOException {
        Visa visa = visaDAO.find(testVisa.getId());
        assertEquals(testVisa.getType(),visa.getType());
    }

    @Test
    public void findAllTest() throws DAOException {
        List<Visa> visas = visaDAO.findAll(Visa.class);
        assertEquals(visas.size(),1);
    }


    @Test
    public void deleteTest() throws DAOException {
        Visa visa = visaDAO.find(testVisa.getId());
        visaDAO.deleteById(visa.getId());
        visa =  visaDAO.find(testVisa.getId());
        assertNull(visa);
    }

    @Test
    public void findVisasByCountryTest() throws DAOException {
        List<Visa> visas = visaDAO.findVisasByCountry(testVisa.getCountry());
        assertEquals(testVisa,visas.get(0));
    }

}
