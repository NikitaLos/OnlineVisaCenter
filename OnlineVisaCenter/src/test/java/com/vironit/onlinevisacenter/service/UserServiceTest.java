package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest extends BaseServiceTest{
    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Test
    public void registerTest() throws DuplicateException, UserServiceException, EntitySaveException {
        User user = entityHelper.prepareUser();
        userService.register(user);
        verify(userDAO,times(1)).save(user);
        verify(userDAO,times(1)).checkDuplicate(user);
    }

    @Test
    public void logInTest() throws EntityFindException, UserServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.findUserByLoginAndPassword(user)).thenReturn(user);
        assertEquals(user,userService.logIn(user));
    }

    @Test
    public void findAllEmployeesTest() throws EntityFindException, UserServiceException {
        User[] users = {entityHelper.prepareUser(),entityHelper.prepareUser()};
        when(userDAO.findAllEmployees()).thenReturn(Arrays.asList(users));
        assertEquals(2,userService.findAllEmployees().size());
    }

    @Test
    public void deleteUserByIdTest() throws UserServiceException, EntityDeleteException {
        userService.deleteUserById(1);
        verify(userDAO,times(1)).deleteById(1);
    }

    @Test
    public void getUserByLoginTest() throws EntityFindException, UserServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.findUserByLogin("login")).thenReturn(user);
        assertEquals(user,userService.getUserByLogin("login"));
    }

    @Test
    public void getUserTest() throws EntityFindException, UserServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.find(1)).thenReturn(user);
        assertEquals(user,userService.getUser(1));
    }

}
