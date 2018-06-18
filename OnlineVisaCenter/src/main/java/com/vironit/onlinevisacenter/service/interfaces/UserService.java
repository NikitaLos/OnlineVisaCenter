package com.vironit.onlinevisacenter.service.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    void register(User user) throws ServiceException;
    @Transactional
    void deleteUserById(Integer id) throws ServiceException;
    User logIn(User user) throws ServiceException;
    List<User> findAllEmployees() throws ServiceException;
    User getUser(Integer id) throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;

}
