package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.ApplicationStarter;
import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@Transactional
public class UserDAOTest {

    @Autowired
    private  UserDAO userDAO;

    @PersistenceContext
    private  EntityManager entityManager;

    private User testUser;

    @Before
    public void insertCountry(){
        testUser = DAOTestUtil.prepareUser();
        entityManager.persist(testUser);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from User").executeUpdate();
    }

    @Test
    public void saveTest() throws EntitySaveException {
        User userExpected = DAOTestUtil.prepareUser();
        userDAO.save(userExpected);
        User userActual = entityManager.find(User.class,userExpected.getId());
        assertEquals(userExpected,userActual);
    }

    @Test
    public void updateTest() throws EntityUpdateException {
        testUser.setLogin("New Test Login");
        userDAO.update(testUser);
        testUser = entityManager.find(User.class, testUser.getId());
        assertEquals("New Test Login", testUser.getLogin());
    }

    @Test
    public void findTest() throws EntityFindException {
        User user = userDAO.find(testUser.getId());
        assertEquals(testUser.getLogin(),user.getLogin());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<User> user = userDAO.findAll(User.class);
        assertEquals(user.size(),1);
    }

    @Test(expected = DuplicateException.class)
    public void isDuplicateTest() throws DuplicateException {
        User user = new User();
        user.setLogin(testUser.getLogin());
        user.setEmail(testUser.getEmail());
        userDAO.checkDuplicate(user);
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        User user = entityManager.find(User.class, testUser.getId());
        userDAO.delete(user);
        user =  entityManager.find(User.class, testUser.getId());
        assertNull(user);
    }

    @Test
    public void getUserByLoginAndPasswordTest() throws  EntityFindException {
        User user = userDAO.findUserByLoginAndPassword(testUser);;
        assertEquals(testUser,user);
    }
}
