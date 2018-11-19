package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CountryDAOTest extends AbstractDAOTest {

    @Autowired
    private CountryDAO countryDAO;

    private Country testCountry;

    @Before
    public void insertCountry(){
        testCountry = jpaRepositoryTestData.prepareCountry();
        entityManager.persist(testCountry);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from Country").executeUpdate();
    }

    @Test
    public void saveTest() {
        Country countryExpected = jpaRepositoryTestData.prepareCountry();
        countryDAO.save(countryExpected);
        Country countryActual = entityManager.find(Country.class,countryExpected.getId());
        Assert.assertEquals(countryExpected,countryActual);
    }

    @Test
    public void findTest() {
        Country country = countryDAO.findById(testCountry.getId()).get();
        assertEquals(testCountry.getName(),country.getName());
    }

    @Test
    public void updateTest() {
        testCountry.setName("New Test Country");
        countryDAO.save(testCountry);
        testCountry = entityManager.find(Country.class,testCountry.getId());
        assertEquals("New Test Country",testCountry.getName());
    }

    @Test
    public void deleteTest() {
        Country country = entityManager.find(Country.class,testCountry.getId());
        countryDAO.deleteById(country.getId());
        country =  entityManager.find(Country.class,testCountry.getId());
        assertNull(country);
    }

    @Test
    public void findAllTest() {
        List<Country> countries = countryDAO.findAll();
        assertEquals(countries.size(),1);
    }

}
