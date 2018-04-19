package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.dao.jpa.DocumentTypeDAOImpl;
import com.vironit.onlinevisacenter.dao.jpa.JPAUtil;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentTypeDAOTest {

    private static DocumentTypeDAO documentTypeDAO;
    private static EntityManager entityManager;
    private DocumentType testDocumentType;

    @BeforeClass
    public static void init(){
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        documentTypeDAO = new DocumentTypeDAOImpl(entityManager);
    }

    @Before
    public void insertDocumentType(){
        testDocumentType = new DocumentType();
        testDocumentType.setName("Test Document Type");
        entityManager.getTransaction().begin();
        entityManager.persist(testDocumentType);
        entityManager.getTransaction().commit();
    }


    @After
    public void deleteCountry(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from DocumentType where name = 'Test Document Type' or name='New Test Document Type'")
                .executeUpdate();
        entityManager.getTransaction().commit();
    }


    @Test
    public void saveTest() throws EntitySaveException {
        DocumentType documentType = new DocumentType();
        documentType.setName("Test Document Type");
        documentTypeDAO.save(documentType);
        assertNotNull(documentType.getId());
    }

    @Test
    public void updateTest() throws EntityUpdateException {
        testDocumentType.setName("New Test Document Type");
        documentTypeDAO.update(testDocumentType);
        testDocumentType = entityManager.find(DocumentType.class,testDocumentType.getId());
        assertEquals("New Test Document Type",testDocumentType.getName());
    }

    @Test
    public void findTest() throws EntityFindException {
        DocumentType documentType = documentTypeDAO.find(testDocumentType.getId());
        assertEquals(testDocumentType.getName(),documentType.getName());
    }

    @Test
    public void findAllTest() throws EntityFindException {
        List<DocumentType> documentTypes = documentTypeDAO.findAll(DocumentType.class);
        assertEquals(documentTypes.size(),1);
    }

    @Test
    public void isDuplicateTest() throws EntityFindException {
        DocumentType documentType = new DocumentType();
        documentType.setName(testDocumentType.getName());
        assertTrue(documentTypeDAO.isDuplicate(documentType));
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        DocumentType documentType = entityManager.find(DocumentType.class,testDocumentType.getId());
        documentTypeDAO.delete(documentType);
        documentType =  entityManager.find(DocumentType.class,testDocumentType.getId());
        assertNull(documentType);
    }
}
