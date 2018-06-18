package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentTypeDAOTest extends BaseDAOTest{

    @Autowired
    private DocumentTypeDAO documentTypeDAO;

    private DocumentType testDocumentType;

    @Before
    public void insertDocumentType(){
        testDocumentType = entityHelper.prepareDocumentType();
        entityManager.persist(testDocumentType);
    }

    @After
    public void deleteCountry(){
        entityManager.createQuery("delete from DocumentType").executeUpdate();
    }

    @Test
    public void saveTest() throws DAOException {
        DocumentType documentTypeExpected = entityHelper.prepareDocumentType();
        documentTypeDAO.save(documentTypeExpected);
        DocumentType documentTypeActual = entityManager.find(DocumentType.class,documentTypeExpected.getId());
        assertEquals(documentTypeExpected,documentTypeActual);
    }

    @Test
    public void updateTest() throws DAOException {
        testDocumentType.setName("New Test Document Type");
        documentTypeDAO.update(testDocumentType);
        testDocumentType = entityManager.find(DocumentType.class,testDocumentType.getId());
        assertEquals("New Test Document Type",testDocumentType.getName());
    }

    @Test
    public void findTest() throws DAOException {
        DocumentType documentType = documentTypeDAO.find(testDocumentType.getId());
        assertEquals(testDocumentType.getName(),documentType.getName());
    }

    @Test
    public void findAllTest() throws DAOException {
        List<DocumentType> documentTypes = documentTypeDAO.findAll(DocumentType.class);
        assertEquals(documentTypes.size(),1);
    }


    @Test
    public void deleteTest() throws DAOException {
        DocumentType documentType = entityManager.find(DocumentType.class,testDocumentType.getId());
        documentTypeDAO.deleteById(documentType.getId());
        documentType =  entityManager.find(DocumentType.class,testDocumentType.getId());
        assertNull(documentType);
    }
}
