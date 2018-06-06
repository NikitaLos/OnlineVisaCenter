package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CountryDAOTest extends BaseDAOTest{

    @Autowired
    private CountryDAO countryDAO;

    private Country testCountry;

    @Before
    public void insertCountry(){
        testCountry = entityHelper.prepareCountry();
        entityManager.persist(testCountry);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from Country").executeUpdate();
    }

    @Test
    public void saveTest() throws EntitySaveException {
        Country countryExpected = entityHelper.prepareCountry();
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
