package com.vironit.onlinevisacenter.dao.JDBCImpl;


import com.vironit.onlinevisacenter.entity.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplicationDAOImpl extends AbstractJDBCDAO<Application,Integer> {

    public ApplicationDAOImpl(Connection connection) {
        super(ConnectionProvider.getConnection());
    }

    @Override
    public List<Application> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Application object) throws SQLException {

    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, Application object) throws SQLException {

    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, Application object) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Application object) {

    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public String getIsDuplicateQuery() {
        return null;
    }
}
