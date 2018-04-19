package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.dao.jpa.ApplicationDAOImpl;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationDAOTest {

    private static ApplicationDAO applicationDAO;
    private static EntityManager entityManager;
    private Application testApplication;

    @BeforeClass
    public static void init(){
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        applicationDAO = new ApplicationDAOImpl(entityManager);
    }

    @Before
    public void insertApplication(){
        testApplication = prepareApplicationForInsert();
        entityManager.getTransaction().begin();
        entityManager.persist(testApplication);
        entityManager.getTransaction().commit();
    }

    @After
    public void deleteApplication(){
        entityManager.getTransaction().begin();
        entityManager.remove(testApplication);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from Visa where type='Test Visa Type' or type='New Test Visa'").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from Country where name='Test Country'").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from DocumentType where name='Test Document Type'").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from User where login='Test User'").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void saveTest() throws EntitySaveException {
        Application application = prepareApplicationForInsert();
        applicationDAO.save(application);
        assertNotNull(application.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(application);
        entityManager.getTransaction().commit();
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
        assertEquals(testApplication.getCreationTime(),application.getCreationTime());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Application> applications = applicationDAO.findAll(Application.class);
        assertEquals(applications.size(),1);
    }

    @Test
    public void deleteTest() throws EntityFindException, EntityDeleteException {
        Application application = prepareApplicationForInsert();
        entityManager.getTransaction().begin();
        entityManager.persist(application);
        entityManager.getTransaction().commit();
        applicationDAO.delete(application);
        application =  applicationDAO.find(application.getId());
        assertNull(application);
    }

    private Application prepareApplicationForInsert(){

        Application application = new Application();
        ClientInfo clientInfo = new ClientInfo();
        VisaInfo visaInfo = new VisaInfo();
        Check check = new Check();
        Passport passport = new Passport();
        ClientDocument clientDocument = new ClientDocument();
        User user = new User();
        Visa visa = new Visa();
        DocumentType documentType = new DocumentType();
        Country country = new Country();

        application.setUser(user);
        application.setVisaInfo(visaInfo);
        application.setClientInfo(clientInfo);
        application.setCheck(check);
        application.setComments("Test Comments");

        user.setLogin("Test User");
        user.setEmail("Test Email");
        user.setPassword("Test Password");
        user.setRole(Role.CLIENT);

        clientInfo.setPassport(passport);
        clientInfo.addClientDocument(clientDocument);
        clientInfo.setAimOfVisit(AimOfVisit.BUSSINES);
        clientInfo.setDateOfBirth(LocalDate.now());
        clientInfo.setPhoneNumber("Test Phone Number");
        clientInfo.setPhotoPathOnServer("Test Photo Path");
        clientInfo.setName("Test Name");
        clientInfo.setSurname("Test Surname");
        clientInfo.setSex("Men");

        visaInfo.setVisaPathOnServer("Test Visa Path");
        visaInfo.setVisa(visa);
        visaInfo.setDateOfReceiving(LocalDate.now());
        visaInfo.setDateFrom(LocalDate.now());
        visaInfo.setDateTo(LocalDate.now());
        visaInfo.setNumOfDaysResidence(5);
        visaInfo.setNameOfClient("Test Name");
        visaInfo.setSurnameOfClient("Test Surname");

        check.setPathOnServer("Test Path");
        check.setAmount(123.);
        check.setDateOfPayment(LocalDateTime.now());

        passport.setCountryOfResidence("Test Country");
        passport.setDateOfEnding(LocalDate.now());
        passport.setDateOfReceiving(LocalDate.now());
        passport.setNumber("Test Number");

        clientDocument.setDocumentType(documentType);
        clientDocument.setPathOnServer("path");

        visa.setType("Test Visa Type");
        visa.setPrice(999.);
        visa.setCountry(country);
        visa.addDocumentType(documentType);

        country.setName("Test Country");
        documentType.setName("Test Document Type");

        return application;
    }
}
