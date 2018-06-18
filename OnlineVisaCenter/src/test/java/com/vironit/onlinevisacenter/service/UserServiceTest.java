package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void registerTest() throws ServiceException, DAOException {
        User user = entityHelper.prepareUser();
        userService.register(user);
        verify(userDAO,times(1)).save(user);
        verify(userDAO,times(1)).findUserByLoginOrEmail(user);
    }

    @Test
    public void logInTest() throws DAOException, ServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.findUserByLoginAndPassword(user)).thenReturn(user);
        assertEquals(user,userService.logIn(user));
    }

    @Test
    public void findAllEmployeesTest() throws DAOException, ServiceException {
        User[] users = {entityHelper.prepareUser(),entityHelper.prepareUser()};
        when(userDAO.findAllEmployees()).thenReturn(Arrays.asList(users));
        assertEquals(2,userService.findAllEmployees().size());
    }

    @Test
    public void deleteUserByIdTest() throws ServiceException, DAOException {
        userService.deleteUserById(1);
        verify(userDAO,times(1)).deleteById(1);
    }

    @Test
    public void getUserByLoginTest() throws DAOException, ServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.findUserByLogin("login")).thenReturn(user);
        assertEquals(user,userService.getUserByLogin("login"));
    }

    @Test
    public void getUserTest() throws DAOException, ServiceException {
        User user = entityHelper.prepareUser();
        when(userDAO.find(1)).thenReturn(user);
        assertEquals(user,userService.getUser(1));
    }

    @Test(expected = ServiceException.class)
    public void isDuplicate() throws ServiceException, DAOException {
        User user = entityHelper.prepareUser();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(user);
        when(userDAO.findUserByLoginOrEmail(user)).thenReturn(expectedList);
        userService.register(user);
    }

}
