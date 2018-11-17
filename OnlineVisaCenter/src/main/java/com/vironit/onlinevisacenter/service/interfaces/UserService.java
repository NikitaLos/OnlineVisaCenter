package com.vironit.onlinevisacenter.service.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void register(User user) throws ServiceException;
    void deleteUserById(Integer id) throws ServiceException;
    User logIn(User user) throws ServiceException;
    List<User> findAllEmployees() throws ServiceException;
    User getUser(Integer id) throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;

}
