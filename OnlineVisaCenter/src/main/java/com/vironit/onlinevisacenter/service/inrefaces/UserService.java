package com.vironit.onlinevisacenter.service.inrefaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;

import java.util.List;

public interface UserService {
    void register(User user) throws DuplicateException, UserServiceException;
    void deleteUser(User user) throws UserServiceException;
    User logIn(User user) throws LoginationException;
    List<User> findAllEmployees() throws UserServiceException;
    void deleteUserById(Integer id) throws UserServiceException;
    User getUser(Integer id) throws EntityFindException;
}
