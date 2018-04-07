package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.JDBCImpl.AbstractJDBCDAO;
import com.vironit.onlinevisacenter.dao.JDBCImpl.ConnectionProvider;
import com.vironit.onlinevisacenter.dao.JDBCImpl.CountryJDBCDAOImpl;
import com.vironit.onlinevisacenter.entity.Country;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAOTest {

    private Connection connection = ConnectionProvider.getConnection();
    private int id;


    @BeforeTest
    public void populateCountry() throws SQLException {
        connection.createStatement().execute("insert into visa_center.country (name,visa_id) values ('USA',1)");
        ResultSet resultSet =  connection.createStatement().executeQuery("select id from visa_center.country  where name='USA'");
        resultSet.next();
        id = resultSet.getInt(1);
    }

    @AfterTest
    public void unPopulateCountry() throws SQLException {
        connection.createStatement().executeUpdate("delete from visa_center.country where name='USA'");
    }

    @Test
    public void getCountryTest(){
        AbstractJDBCDAO<Country,Integer> countryDAO = new CountryJDBCDAOImpl();
        Country country = countryDAO.getByPK(id);
        Assert.assertEquals("USA",country.getName());
    }


}
