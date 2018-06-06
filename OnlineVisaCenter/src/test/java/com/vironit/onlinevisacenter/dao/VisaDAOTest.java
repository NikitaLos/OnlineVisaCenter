package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
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
    public void saveTest() throws EntitySaveException {
        Visa visaExpected = entityHelper.prepareVisa();
        visaDAO.save(visaExpected);
        Visa visaActual = entityManager.find(Visa.class,visaExpected.getId());
        assertEquals(visaExpected,visaActual);
    }

    @Test
    public void updateTest() throws EntityUpdateException, EntityFindException {
        testVisa.setType("New Test Visa");
        visaDAO.update(testVisa);
        testVisa = visaDAO.find(testVisa.getId());
        assertEquals("New Test Visa",testVisa.getType());
    }

    @Test
    public void findTest() throws EntityFindException {
        Visa visa = visaDAO.find(testVisa.getId());
        assertEquals(testVisa.getType(),visa.getType());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Visa> visas = visaDAO.findAll(Visa.class);
        assertEquals(visas.size(),1);
    }

    @Test(expected = DuplicateException.class)
    public void isDuplicateTest() throws EntityFindException, DuplicateException {
        Visa visa = new Visa();
        visa.setType(testVisa.getType());
        visa.setCountry(testVisa.getCountry());
        visaDAO.checkDuplicate(visa);
    }

    @Test
    public void deleteTest() throws EntityFindException, EntityDeleteException {
        Visa visa = visaDAO.find(testVisa.getId());
        visaDAO.deleteById(visa.getId());
        visa =  visaDAO.find(testVisa.getId());
        assertNull(visa);
    }

    @Test
    public void findVisasByCountryTest() throws EntityFindException {
        List<Visa> visas = visaDAO.findVisasByCountry(testVisa.getCountry());
        assertEquals(testVisa,visas.get(0));
    }

}
