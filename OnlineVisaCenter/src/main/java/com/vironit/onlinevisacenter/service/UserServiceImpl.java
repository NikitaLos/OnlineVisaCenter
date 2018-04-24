package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws DuplicateException, UserServiceException {
        try {
            if(!userDAO.isDuplicate(user)) {
                userDAO.save(user);
            }else{
                throw new DuplicateException("Such login or email already exists");
            }
        } catch (EntityFindException | EntitySaveException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User logIn(User user) throws LoginationException {
        try {
            return userDAO.getUserByLoginAndPassword(user);
        } catch (EntityFindException e) {
            throw new LoginationException("User not registered");
        }
    }

    @Override
    public List<User> findAllEmployees() throws UserServiceException {
        try {
            return userDAO.findAllEmployees();
        } catch (EntityFindException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void deleteUserById(Integer id) throws UserServiceException {
        try {
            userDAO.deleteById(id);
        } catch (EntityDeleteException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUser(Integer id) throws EntityFindException {
        try {
            return userDAO.find(id);
        } catch (EntityFindException e) {
            throw new EntityFindException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws UserServiceException {
        try {
            userDAO.delete(user);
        } catch (EntityDeleteException e) {
            throw new UserServiceException(e);
        }
    }

}
