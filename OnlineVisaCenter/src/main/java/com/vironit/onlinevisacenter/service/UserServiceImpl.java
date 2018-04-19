package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.dao.jpa.UserDAOImpl;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.DuplicateUserException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl(JPAUtil.getEntityManagerFactory().createEntityManager());
    }

    @Override
    public void register(User user) throws DuplicateUserException, UserServiceException {
        try {
            if(!userDAO.isDuplicate(user)) {
                userDAO.save(user);
            }else{
                throw new DuplicateUserException("Login or email already exist");
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
    public void logOut(User user) {
        
    }

    @Override
    public void deleteUser(User user) {
//        userDAO.delete(user);
    }

}
