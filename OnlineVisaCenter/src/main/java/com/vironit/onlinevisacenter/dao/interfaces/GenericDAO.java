package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T,PK extends Serializable> {
    void save(T t) throws  DAOException;
    void update(T t) throws DAOException;
    void delete(T t) throws DAOException;
    void deleteById(Integer id) throws DAOException;
    T find(PK id) throws DAOException;
    List<T> findAll(Class<T> classType) throws DAOException;
}
