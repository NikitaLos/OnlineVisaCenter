package com.vironit.onlinevisacenter.service.inrefaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.service.DuplicateUserException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;

public interface UserService {
    void register(User user) throws DuplicateUserException, UserServiceException;
    void deleteUser(User user);
    User logIn(User user) throws LoginationException;
    void logOut(User user);

}
