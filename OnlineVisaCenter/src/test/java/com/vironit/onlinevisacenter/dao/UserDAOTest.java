package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.dao.jpa.UserDAOImpl;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {
    private static UserDAO userDAO;
    private static EntityManager entityManager;
    private User testUser;

    @BeforeClass
    public static void init(){
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        userDAO = new UserDAOImpl(entityManager);
    }

    @Before
    public void insertCountry(){
        testUser = new User();
        testUser.setLogin("Test Login");
        testUser.setPassword("Test Password");
        testUser.setRole(Role.CLIENT);
        testUser.setEmail("Test Email");
        entityManager.getTransaction().begin();
        entityManager.persist(testUser);
        entityManager.getTransaction().commit();
    }


    @After
    public void deleteCountry(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User where login = 'Test Login' or login ='New Test Login'").executeUpdate();
        entityManager.getTransaction().commit();
    }


    @Test
    public void saveTest() throws EntitySaveException {
        User user = new User();
        user.setLogin("Test Login");
        user.setPassword("Test Password");
        user.setRole(Role.CLIENT);
        user.setEmail("Test Email");
        userDAO.save(user);
        assertNotNull(user.getId());
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

    @Ignore
    @Test
    public void findAllTest() throws EntityFindException {
        List<User> user = userDAO.findAll(User.class);
        assertEquals(user.size(),1);
    }

    @Test
    public void isDuplicateTest() throws EntityFindException {
        User user = new User();
        user.setLogin(testUser.getLogin());
        user.setEmail(testUser.getEmail());
        assertTrue(userDAO.isDuplicate(user));
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        User user = entityManager.find(User.class, testUser.getId());
        userDAO.delete(user);
        user =  entityManager.find(User.class, testUser.getId());
        assertNull(user);
    }
}
