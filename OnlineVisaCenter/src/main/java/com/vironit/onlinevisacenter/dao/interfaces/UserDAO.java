package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.util.List;

public interface UserDAO {
    void save(User user) throws DAOException;
    void delete(User user) throws DAOException;
    User find(Integer key) throws DAOException;
    void update(User user) throws DAOException;
    List<User> findAll(Class<User> classType) throws DAOException;
    List findUserByLoginOrEmail(User user) throws DAOException;
    User findUserByLoginAndPassword(User user) throws DAOException;
    List<User> findAllEmployees() throws DAOException;
    void deleteById(Integer id) throws DAOException;
    User findUserByLogin(String login) throws DAOException;
}
