package com.vironit.onlinevisacenter.dao.JDBCImpl;


import com.vironit.onlinevisacenter.entity.Identified;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCDAO<T ,PK extends Serializable>  {

    protected Connection connection;

    public AbstractJDBCDAO(Connection connection) {
        this.connection = connection;
    }


    public abstract List<T> parseResultSet(ResultSet rs) throws SQLException;
    public abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;
    public abstract void prepareStatementForCreate(PreparedStatement statement, T object) throws SQLException;
    public abstract void prepareStatementForIsDuplicate(PreparedStatement statement, T object) throws SQLException;
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws SQLException;
    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    public abstract String getIsDuplicateQuery();


    public void create(T object){
        String sql = getCreateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForCreate(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
           //todo
        }
    }


    public void update(T object) {
        String sql = getUpdateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
            //todo
        }

    }

    public void delete(T object) {
        String sql = getDeleteQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForDelete(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
            //todo
        }
    }


    public T getByPK(int key)  {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery()+ " WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet =  statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            //todo
        }
        return list.iterator().next();
    }

    public List<T> getAll()  {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            //todo
        }
        return list;
    }

    public boolean isDuplicate(T object) {
        boolean isDuplicate = false;
        String sql = getIsDuplicateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForIsDuplicate(statement,object);
            ResultSet resultSet =  statement.executeQuery();
            isDuplicate = resultSet.next();
        } catch (SQLException e) {
            //todo
        }
        return isDuplicate;
    }


}
