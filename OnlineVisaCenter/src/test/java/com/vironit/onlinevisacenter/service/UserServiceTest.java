package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.AbstractTest;
import com.vironit.onlinevisacenter.converter.user.UserDTOToUserConverter;
import com.vironit.onlinevisacenter.converter.user.UserToUserDTOConverter;
import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.repository.jpa.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest extends AbstractTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserToUserDTOConverter toUserDTOConverter;

    @MockBean
    private UserDTOToUserConverter toUserConverter;

    @MockBean
    private UserDAO userDAO;

    @Mock
    private UserDTO userDTO;

    @Mock
    private User user;

    @Before
    public void setUp() {
        when(toUserConverter.convert(userDTO)).thenReturn(user);
        when(toUserDTOConverter.convert(user)).thenReturn(userDTO);
        when(user.getEmail()).thenReturn("Test Email");
        when(user.getLogin()).thenReturn("Test User");
        when(user.getPassword()).thenReturn("Test Password");
        when(user.getId()).thenReturn(1);
        when(userDTO.getLogin()).thenReturn("Test User");
        when(userDTO.getPassword()).thenReturn("Test Password");
    }

    @Test
    public void getCountryTest() {
        when(userDAO.findById(1)).thenReturn(Optional.of(user));
        assertEquals(userDTO,userService.getUser(1));
    }

    @Test
    public void registerTest() {
        userService.register(userDTO);
        verify(userDAO, times(1)).save(user);
        verify(userDAO, times(1)).findByLoginOrEmail(user.getLogin(), user.getEmail());
    }

    @Test
    public void logInTest() {
        userService.logIn(userDTO);
        verify(userDAO, times(1)).findByLoginAndPassword(user.getLogin(), user.getPassword());
    }

    @Test
    public void findAllEmployeesTest() {
        User[] users = {user, user};
        when(userDAO.findAllEmployees()).thenReturn(Arrays.asList(users));
        assertEquals(2, userService.findAllEmployees().size());
    }

    @Test
    public void deleteUserByIdTest() {
        userService.deleteUserById(1);
        verify(userDAO, times(1)).deleteById(1);
    }

    @Test
    public void getUserByLoginTest() {
        when(userDAO.findByLogin("Test User")).thenReturn(user);
        assertEquals(user, toUserConverter.convert(userService.getUserByLogin("Test User")));
    }

    @Test(expected = DuplicateException.class)
    public void isDuplicate() {
        List<User> expectedList = new ArrayList<>();
        expectedList.add(user);
        when(userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail())).thenReturn(expectedList);
        userService.register(userDTO);
    }

}
