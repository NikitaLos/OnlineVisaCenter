package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.dao.jpa.CountryDAOImpl;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;

import static org.junit.Assert.*;

import javax.persistence.*;
import java.util.List;


public class CountryDAOTest {

    private static CountryDAO countryDAO;
    private static EntityManager entityManager;
    private Country testCountry;

    @BeforeClass
    public static void init(){
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        countryDAO = new CountryDAOImpl(entityManager);
    }

    @Before
    public void insertCountry(){
        testCountry = new Country();
        testCountry.setName("Test Country");
        entityManager.getTransaction().begin();
        entityManager.persist(testCountry);
        entityManager.getTransaction().commit();
    }


    @After
    public void deleteCountry(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Country where name = 'Test Country' or name='New Test Country'").executeUpdate();
        entityManager.getTransaction().commit();
    }


    @Test
    public void saveTest() throws EntitySaveException {
        Country country = new Country();
        country.setName("Test Country");
        countryDAO.save(country);
        assertNotNull(country.getId());
    }

    @Test
    public void updateTest() throws EntityUpdateException{
        testCountry.setName("New Test Country");
        countryDAO.update(testCountry);
        testCountry = entityManager.find(Country.class,testCountry.getId());
        assertEquals("New Test Country",testCountry.getName());
    }

    @Test
    public void findTest() throws EntityFindException {
        Country country = countryDAO.find(testCountry.getId());
        assertEquals(testCountry.getName(),country.getName());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Country> countries = countryDAO.findAll(Country.class);
        assertEquals(countries.size(),1);
    }

    @Test
    public void isDuplicateTest() throws EntityFindException {
        Country country = new Country();
        country.setName(testCountry.getName());
        assertTrue(countryDAO.isDuplicate(country));
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        Country country = entityManager.find(Country.class,testCountry.getId());
        countryDAO.delete(country);
        country =  entityManager.find(Country.class,testCountry.getId());
        assertNull(country);
    }
}
