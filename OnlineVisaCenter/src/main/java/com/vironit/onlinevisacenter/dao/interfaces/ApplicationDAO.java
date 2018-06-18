package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.util.List;

public interface ApplicationDAO extends GenericDAO<Application,Integer>{
    void save(Application application) throws DAOException;
    void delete(Application application) throws DAOException;
    Application find(Integer key) throws DAOException;
    void update(Application application) throws DAOException;
    List<Application> findAll(Class<Application> classType) throws DAOException;
    List<Application> findApplicationsByClient(Integer userId) throws DAOException;
    void deleteById(Integer id) throws DAOException;
}
