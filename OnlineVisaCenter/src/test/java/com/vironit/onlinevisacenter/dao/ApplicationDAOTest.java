package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.ApplicationStarter;
import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.*;

import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class,JPAConfigTest.class})
@ActiveProfiles("test")
@Transactional
public class ApplicationDAOTest {

    @Autowired
    private ApplicationDAO applicationDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private Application testApplication;

    @Before
    public void insertApplication() {
        testApplication = DAOTestUtil.prepareApplicationForInsert();
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
    public void saveTest() throws EntitySaveException {
        Application applicationExpected = DAOTestUtil.prepareApplicationForInsert();
        applicationDAO.save(applicationExpected);
        Application applicationActual = entityManager.find(Application.class, applicationExpected.getId());
        assertEquals(applicationExpected, applicationActual);
    }

    @Test
    public void updateTest() throws EntityUpdateException, EntityFindException {
        testApplication.setComments("New Test Comments");
        applicationDAO.update(testApplication);
        testApplication = applicationDAO.find(testApplication.getId());
        assertEquals("New Test Comments", testApplication.getComments());
    }

    @Test
    public void findTest() throws EntityFindException {
        Application application = applicationDAO.find(testApplication.getId());
        assertEquals(testApplication.getCreationTime(), application.getCreationTime());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Application> applications = applicationDAO.findAll(Application.class);
        assertEquals(applications.size(), 1);
    }

    @Test
    public void deleteTest() throws EntityFindException, EntityDeleteException {
        Application application = entityManager.find(Application.class, testApplication.getId());
        applicationDAO.delete(application);
        application = applicationDAO.find(application.getId());
        assertNull(application);
    }

    @Test
    public void findApplicationByClientTest() throws EntityFindException {
        User user = testApplication.getUser();
        List<Application> applications = applicationDAO.findApplicationsByClient(user.getId());
        assertEquals(testApplication,applications.get(0));
    }

}