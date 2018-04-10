package com.vironit.onlinevisacenter.dao.JDBCImpl;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryJDBCDAOImpl extends AbstractJDBCDAO<Country> implements CountryDAO {


    private String selectQuery = "select * from visa_center.country";
    private String deleteQuery = "delete from visa_center.country where id = ?";
    private String updateQuery = "update visa_center.country set name = ? where id = ?";
    private String createQuery = "insert into visa_center.country (name) values(?)";
    private String isDuplicateQuery = "select from visa_center.country where name = ?";

    public CountryJDBCDAOImpl() {
        super(ConnectionProvider.getConnection(),Country.class);
    }

    @Override
    public List<Country> parseResultSet(ResultSet rs) throws SQLException {
        List<Country> countries = new ArrayList<>();
        while (rs.next()){
            Country country = new Country();
            country.setId(rs.getInt("id"));
            country.setName(rs.getString("name"));
            countries.add(country);
        }
        return countries;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Country country) throws SQLException {
        statement.setString(1,country.getName());
        statement.setInt(2,country.getId());
    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, Country country) throws SQLException {
        statement.setString(1,country.getName());
    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, Country country) throws SQLException {
        statement.setString(1,country.getName());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Country country) throws SQLException {
        statement.setInt(1,country.getId());
    }

    @Override
    public String getSelectQuery() {
        return selectQuery;
    }

    @Override
    public String getCreateQuery() {
        return createQuery;
    }

    @Override
    public String getUpdateQuery() {
        return updateQuery;
    }

    @Override
    public String getDeleteQuery() {
        return deleteQuery;
    }

    @Override
    public String getIsDuplicateQuery() {
        return isDuplicateQuery;
    }


}
