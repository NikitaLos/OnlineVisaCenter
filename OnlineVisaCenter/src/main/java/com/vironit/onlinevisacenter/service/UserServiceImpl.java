package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;

public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) {
//        if(!userDAO.isDuplicate(user)) {
//            userDAO.save(user);
//        }else{
//            //todo exception
//        }
    }

    @Override
    public User logIn(User user) {
//        return userDAO.getUserByLoginAndPassword(user);
        return null;

    }

    @Override
    public void logOut(User user) {
        
    }

    @Override
    public void deleteUser(User user) {
//        userDAO.delete(user);
    }

    //todo ban user,

}
