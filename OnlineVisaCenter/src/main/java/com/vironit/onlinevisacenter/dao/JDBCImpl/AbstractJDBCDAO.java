package com.vironit.onlinevisacenter.dao.JDBCImpl;


import com.vironit.onlinevisacenter.ServerLogger;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCDAO<T>{

    protected Connection connection;
    protected ServerLogger logger;

    public AbstractJDBCDAO(Connection connection,Class classType) {
        this.connection = connection;
        this.logger = new ServerLogger(classType);
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


    public void save(T object) throws EntitySaveException {
        String sql = getCreateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForCreate(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("save entity error",e);
            throw new EntitySaveException(e);
        }
    }


    public void update(T object) throws EntityUpdateException {
        String sql = getUpdateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("update entity error",e);
            throw new EntityUpdateException(e);
        }

    }

    public void delete(T object) throws EntityDeleteException {
        String sql = getDeleteQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForDelete(statement,object);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("delete entity error",e);
            throw new EntityDeleteException(e);
        }
    }


    public T find(Integer key) throws EntityFindException {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery()+ " WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet =  statement.executeQuery();
            list = parseResultSet(resultSet);
            return list.iterator().next();
        } catch (SQLException e) {
            logger.error("find entity error",e);
            throw new EntityFindException(e);
        }
    }

    public List<T> findAll(Class<T> classType) throws EntityFindException {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  statement.executeQuery();
            list = parseResultSet(resultSet);
            return list;
        } catch (SQLException e) {
            logger.error("findALL entity error",e);
            throw new EntityFindException(e);
        }
    }

    public boolean isDuplicate(T object) throws EntityFindException {
        boolean isDuplicate = false;
        String sql = getIsDuplicateQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForIsDuplicate(statement,object);
            ResultSet resultSet =  statement.executeQuery();
            isDuplicate = resultSet.next();
            return isDuplicate;
        } catch (SQLException e) {
            logger.error("duplicate check error",e);
            throw new EntityFindException(e);
        }
    }


}
