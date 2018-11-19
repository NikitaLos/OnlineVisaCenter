package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class VisaDAOTest extends AbstractDAOTest {

    @Autowired
    private VisaDAO visaDAO;

    private Visa testVisa;

    private Country country;

    private DocumentType documentType;

    @Before
    public void insertCountry() {
        country = jpaRepositoryTestData.prepareCountry();
        entityManager.persist(country);
        documentType = jpaRepositoryTestData.prepareDocumentType();
        entityManager.persist(documentType);
        testVisa = jpaRepositoryTestData.prepareVisa(country, documentType);
        entityManager.persist(testVisa);
    }

    @After
    public void deleteCountry() {
        entityManager.createQuery("DELETE from Visa").executeUpdate();
        entityManager.createQuery("DELETE from Country").executeUpdate();
        entityManager.createQuery("DELETE from DocumentType").executeUpdate();
    }

    @Test
    public void saveTest() {
        Visa visaExpected = jpaRepositoryTestData.prepareVisa(country, documentType);
        visaDAO.save(visaExpected);
        Visa visaActual = entityManager.find(Visa.class, visaExpected.getId());
        assertEquals(visaExpected, visaActual);
    }

    @Test
    public void updateTest() {
        testVisa.setType("New Test Visa");
        visaDAO.save(testVisa);
        testVisa = entityManager.find(Visa.class, testVisa.getId());
        assertEquals("New Test Visa", testVisa.getType());
    }

    @Test
    public void findTest() {
        Visa visa = visaDAO.findById(testVisa.getId()).get();
        assertEquals(testVisa.getType(), visa.getType());
    }

    @Test
    public void findAllTest() {
        List<Visa> visas = visaDAO.findAll();
        assertEquals(visas.size(), 1);
    }

    @Test
    public void deleteTest() {
        Visa visa = entityManager.find(Visa.class, testVisa.getId());
        visaDAO.deleteById(visa.getId());
        visa = entityManager.find(Visa.class, testVisa.getId());
        assertNull(visa);
    }

    @Test
    public void findVisasByCountryTest() {
        List<Visa> visas = visaDAO.findByCountryId(testVisa.getCountry().getId());
        assertEquals(testVisa, visas.get(0));
    }

}
