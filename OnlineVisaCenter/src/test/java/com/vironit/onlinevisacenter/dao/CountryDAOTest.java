package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.ApplicationStarter;
import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class,JPAConfigTest.class})
@ActiveProfiles("test")
@Transactional
public class CountryDAOTest {

    @Autowired
    private CountryDAO countryDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private Country testCountry;

    @Before
    public void insertCountry(){
        testCountry = DAOTestUtil.prepareCountry();
        entityManager.persist(testCountry);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from Country").executeUpdate();
    }

    @Test
    public void saveTest() throws EntitySaveException {
        Country countryExpected = DAOTestUtil.prepareCountry();
        countryDAO.save(countryExpected);
        Country countryActual = entityManager.find(Country.class,countryExpected.getId());
        Assert.assertEquals(countryExpected,countryActual);
    }

    @Test
    public void findTest() throws EntityFindException {
        Country country = countryDAO.find(testCountry.getId());
        assertEquals(testCountry.getName(),country.getName());
    }

    @Test
    public void updateTest() throws EntityUpdateException {
        testCountry.setName("New Test Country");
        countryDAO.update(testCountry);
        testCountry = entityManager.find(Country.class,testCountry.getId());
        assertEquals("New Test Country",testCountry.getName());
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        Country country = entityManager.find(Country.class,testCountry.getId());
        countryDAO.deleteById(country.getId());
        country =  entityManager.find(Country.class,testCountry.getId());
        assertNull(country);
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Country> countries = countryDAO.findAll(Country.class);
        assertEquals(countries.size(),1);
    }

    @Test(expected = DuplicateException.class)
    public void isDuplicateTest() throws EntityFindException, DuplicateException {
        Country country = new Country();
        country.setName(testCountry.getName());
        countryDAO.checkDuplicate(country);
    }

}
