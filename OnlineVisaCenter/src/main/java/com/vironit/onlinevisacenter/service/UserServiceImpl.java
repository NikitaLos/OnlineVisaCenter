package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws ServiceException {
        try {
            checkDuplicate(user);
            userDAO.save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User logIn(User user) throws ServiceException {

        try {
            return userDAO.findUserByLoginAndPassword(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<User> findAllEmployees() throws ServiceException {
        try {
            return userDAO.findAllEmployees();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUserById(Integer id) throws ServiceException {
        try {
            userDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(Integer id) throws ServiceException {
        try {
            return userDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void checkDuplicate(User user) throws ServiceException, DAOException {
        if (!userDAO.findUserByLoginOrEmail(user).isEmpty()){
            throw new ServiceException("Such a user already exists");
        }
    }
}
