package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest extends BaseDAOTest{

    @Autowired
    private  UserDAO userDAO;

    private User testUser;

    @Before
    public void insertCountry(){
        testUser = entityHelper.prepareUser();
        entityManager.persist(testUser);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from User").executeUpdate();
    }

    @Test
    public void saveTest() throws DAOException {
        User userExpected = entityHelper.prepareUser();
        userDAO.save(userExpected);
        User userActual = entityManager.find(User.class,userExpected.getId());
        assertEquals(userExpected,userActual);
    }

    @Test
    public void updateTest() throws DAOException {
        testUser.setLogin("New Test Login");
        userDAO.update(testUser);
        testUser = entityManager.find(User.class, testUser.getId());
        assertEquals("New Test Login", testUser.getLogin());
    }

    @Test
    public void findTest() throws DAOException {
        User user = userDAO.find(testUser.getId());
        assertEquals(testUser.getLogin(),user.getLogin());
    }

    @Test
    public void findAllTest() throws DAOException {
        List<User> user = userDAO.findAll(User.class);
        assertEquals(user.size(),1);
    }

    @Test
    public void deleteTest() throws DAOException {
        User user = entityManager.find(User.class, testUser.getId());
        userDAO.delete(user);
        user =  entityManager.find(User.class, testUser.getId());
        assertNull(user);
    }

    @Test
    public void getUserByLoginAndPasswordTest() throws DAOException {
        User user = userDAO.findUserByLoginAndPassword(testUser);
        assertEquals(testUser,user);
    }
}
