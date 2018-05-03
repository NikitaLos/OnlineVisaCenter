package com.vironit.onlinevisacenter.service.inrefaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    void register(User user) throws DuplicateException, UserServiceException;

    @Transactional
    void deleteUser(User user) throws UserServiceException;

    @Transactional
    void deleteUserById(Integer id) throws UserServiceException;

    User logIn(User user) throws LoginationException;

    List<User> findAllEmployees() throws UserServiceException;

    User getUser(Integer id) throws EntityFindException;
}
