package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.dao.jpa.VisaDAOImpl;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import java.util.List;

public class VisaDAOTest {

    private static VisaDAO visaDAO;
    private static EntityManager entityManager;
    private Visa testVisa;

    @BeforeClass
    public static void init(){
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        visaDAO = new VisaDAOImpl(entityManager);
    }

    @Before
    public void insertCountry(){
        testVisa = prepareVisaForInsert();
        entityManager.getTransaction().begin();
        entityManager.persist(testVisa);
        entityManager.getTransaction().commit();
    }

    @After
    public void deleteCountry(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from Visa where type='Test Visa Type' or type='New Test Visa'").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from Country where name='Test Country'").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from DocumentType where name='Test Document Type'").executeUpdate();
        entityManager.getTransaction().commit();
    }


    @Test
    public void saveTest() throws EntitySaveException {
        Visa visa = prepareVisaForInsert();
        visaDAO.save(visa);
        assertNotNull(visa.getId());
    }

    @Test
    public void updateTest() throws EntityUpdateException, EntityFindException {
        testVisa.setType("New Test Visa");
        visaDAO.update(testVisa);
        testVisa = visaDAO.find(testVisa.getId());
        assertEquals("New Test Visa",testVisa.getType());
    }

    @Test
    public void findTest() throws EntityFindException {
        Visa visa = visaDAO.find(testVisa.getId());
        assertEquals(testVisa.getType(),visa.getType());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<Visa> visas = visaDAO.findAll(Visa.class);
        assertEquals(visas.size(),1);
    }

    @Test
    public void isDuplicateTest() throws EntityFindException {
        Visa visa = new Visa();
        visa.setType(testVisa.getType());
        visa.setCountry(testVisa.getCountry());
        assertTrue(visaDAO.isDuplicate(visa));
    }

    @Test
    public void deleteTest() throws EntityFindException, EntityDeleteException {
        Visa visa = visaDAO.find(testVisa.getId());
        visaDAO.delete(visa);
        visa =  visaDAO.find(testVisa.getId());
        assertNull(visa);
    }

    private Visa prepareVisaForInsert(){
        Visa visa = new Visa();

        Country country = new Country();
        country.setName("Test Country");

        DocumentType documentType = new DocumentType();
        documentType.setName("Test Document Type");

        visa.setType("Test Visa Type");
        visa.setPrice(999.);
        visa.setCountry(country);
        visa.addDocumentType(documentType);
        return visa;
    }
}
