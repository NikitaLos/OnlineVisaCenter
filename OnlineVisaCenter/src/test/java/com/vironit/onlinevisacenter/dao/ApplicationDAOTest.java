package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationDAOTest extends BaseDAOTest {

    @Autowired
    private ApplicationDAO applicationDAO;

    private Application testApplication;

    @Before
    public void insertApplication() {
        testApplication = entityHelper.prepareApplication();
        entityManager.persist(testApplication);
    }

    @After
    public void deleteApplication() {
        entityManager.createQuery("DELETE from Application").executeUpdate();
        entityManager.createQuery("DELETE from VisaInfo ").executeUpdate();
        entityManager.createQuery("DELETE from ClientInfo ").executeUpdate();
        entityManager.createQuery("DELETE from Passport ").executeUpdate();
        entityManager.createQuery("DELETE from Visa").executeUpdate();
        entityManager.createQuery("DELETE from Country").executeUpdate();
        entityManager.createQuery("DELETE from DocumentType").executeUpdate();
        entityManager.createQuery("DELETE from User").executeUpdate();
    }

    @Test
    public void saveTest() throws DAOException {
        Application applicationExpected = entityHelper.prepareApplication();
        applicationDAO.save(applicationExpected);
        Application applicationActual = entityManager.find(Application.class, applicationExpected.getId());
        assertEquals(applicationExpected, applicationActual);
    }

    @Test
    public void updateTest() throws DAOException {
        testApplication.setComments("New Test Comments");
        applicationDAO.update(testApplication);
        testApplication = applicationDAO.find(testApplication.getId());
        assertEquals("New Test Comments", testApplication.getComments());
    }

    @Test
    public void findTest() throws DAOException {
        Application application = applicationDAO.find(testApplication.getId());
        assertEquals(testApplication.getCreationTime(), application.getCreationTime());
    }

    @Test
    public void findAllTest() throws DAOException {
        List<Application> applications = applicationDAO.findAll(Application.class);
        assertEquals(applications.size(), 1);
    }

    @Test
    public void deleteTest() throws DAOException {
        Application application = entityManager.find(Application.class, testApplication.getId());
        applicationDAO.delete(application);
        application = applicationDAO.find(application.getId());
        assertNull(application);
    }

    @Test
    public void findApplicationByClientTest() throws DAOException {
        User user = testApplication.getUser();
        List<Application> applications = applicationDAO.findApplicationsByClient(user.getId());
        assertEquals(testApplication,applications.get(0));
    }

}