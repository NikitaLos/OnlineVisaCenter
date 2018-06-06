package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
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
    public void saveTest() throws EntitySaveException {
        DocumentType documentTypeExpected = entityHelper.prepareDocumentType();
        documentTypeDAO.save(documentTypeExpected);
        DocumentType documentTypeActual = entityManager.find(DocumentType.class,documentTypeExpected.getId());
        assertEquals(documentTypeExpected,documentTypeActual);
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

    @Test(expected = DuplicateException.class)
    public void isDuplicateTest() throws EntityFindException, DuplicateException {
        DocumentType documentType = new DocumentType();
        documentType.setName(testDocumentType.getName());
        documentTypeDAO.checkDuplicate(documentType);
    }

    @Test
    public void deleteTest() throws EntityDeleteException {
        DocumentType documentType = entityManager.find(DocumentType.class,testDocumentType.getId());
        documentTypeDAO.deleteById(documentType.getId());
        documentType =  entityManager.find(DocumentType.class,testDocumentType.getId());
        assertNull(documentType);
    }
}
