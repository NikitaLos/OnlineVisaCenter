package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest extends AbstractDAOTest {

    @Autowired
    private  UserDAO userDAO;

    private User testUser;

    @Before
    public void insertCountry(){
        testUser = jpaRepositoryTestData.prepareUser();
        entityManager.persist(testUser);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from User").executeUpdate();
    }

    @Test
    public void saveTest() {
        User userExpected = jpaRepositoryTestData.prepareUser();
        userDAO.save(userExpected);
        User userActual = entityManager.find(User.class,userExpected.getId());
        assertEquals(userExpected,userActual);
    }

    @Test
    public void updateTest() {
        testUser.setLogin("New Test Login");
        userDAO.save(testUser);
        testUser = entityManager.find(User.class, testUser.getId());
        assertEquals("New Test Login", testUser.getLogin());
    }

    @Test
    public void findTest() {
        User user = userDAO.findById(testUser.getId()).get();
        assertEquals(testUser.getLogin(),user.getLogin());
    }

    @Test
    public void findAllTest() {
        List<User> user = userDAO.findAll();
        assertEquals(user.size(),1);
    }

    @Test
    public void deleteTest() {
        User user = entityManager.find(User.class, testUser.getId());
        userDAO.delete(user);
        user =  entityManager.find(User.class, testUser.getId());
        assertNull(user);
    }

    @Test
    public void getUserByLoginAndPasswordTest() {
        User user = userDAO.findByLoginAndPassword(testUser.getLogin(),testUser.getPassword());
        assertEquals(testUser,user);
    }
}
