package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApplicationDAOTest extends AbstractDAOTest {

    @Autowired
    private ApplicationDAO applicationDAO;

    private Application testApplication;

    private Visa testVisa;

    private User testUser;

    @Before
    public void insertApplication() {
        testUser = jpaRepositoryTestData.prepareUser();
        entityManager.persist(testUser);
        Country country = jpaRepositoryTestData.prepareCountry();
        entityManager.persist(country);
        DocumentType documentType = jpaRepositoryTestData.prepareDocumentType();
        entityManager.persist(documentType);
        testVisa = jpaRepositoryTestData.prepareVisa(country, documentType);
        entityManager.persist(testVisa);
        testApplication = jpaRepositoryTestData.prepareApplication(testUser,testVisa);
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
    public void saveTest() {
        Application applicationExpected = jpaRepositoryTestData.prepareApplication(testUser,testVisa);
        applicationDAO.save(applicationExpected);
        Application applicationActual = entityManager.find(Application.class, applicationExpected.getId());
        assertEquals(applicationExpected, applicationActual);
    }

    @Test
    public void updateTest() {
        testApplication.setComments("New Test Comments");
        applicationDAO.save(testApplication);
        testApplication = entityManager.find(Application.class,testApplication.getId());
        assertEquals("New Test Comments", testApplication.getComments());
    }

    @Test
    public void findTest() {
        Application application = applicationDAO.findById(testApplication.getId()).get();
        assertEquals(testApplication.getCreationTime(), application.getCreationTime());
    }

    @Test
    public void findAllTest() {
        List<Application> applications = applicationDAO.findAll();
        assertEquals(applications.size(), 1);
    }

    @Test
    public void deleteTest() {
        Application application = entityManager.find(Application.class, testApplication.getId());
        applicationDAO.delete(application);
        application = entityManager.find(Application.class,application.getId());
        assertNull(application);
    }

    @Test
    public void findApplicationByClientTest() {
        User user = testApplication.getUser();
        List<Application> applications = applicationDAO.findByUserId(user.getId());
        assertEquals(testApplication, applications.get(0));
    }

}