package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.DocumentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DocumentTypeDAOTest extends AbstractDAOTest {

    @Autowired
    private DocumentTypeDAO documentTypeDAO;

    private DocumentType testDocumentType;

    @Before
    public void insertDocumentType() {
        testDocumentType = jpaRepositoryTestData.prepareDocumentType();
        entityManager.persist(testDocumentType);
    }

    @After
    public void deleteCountry() {
        entityManager.createQuery("delete from DocumentType").executeUpdate();
    }

    @Test
    public void saveTest() {
        DocumentType documentTypeExpected = jpaRepositoryTestData.prepareDocumentType();
        documentTypeDAO.save(documentTypeExpected);
        DocumentType documentTypeActual = entityManager.find(DocumentType.class, documentTypeExpected.getId());
        assertEquals(documentTypeExpected, documentTypeActual);
    }

    @Test
    public void updateTest() {
        testDocumentType.setName("New Test Document Type");
        documentTypeDAO.save(testDocumentType);
        testDocumentType = entityManager.find(DocumentType.class, testDocumentType.getId());
        assertEquals("New Test Document Type", testDocumentType.getName());
    }

    @Test
    public void findTest() {
        DocumentType documenttype = documentTypeDAO.findById(testDocumentType.getId()).get();
        assertEquals(testDocumentType.getName(), documenttype.getName());
    }

    @Test
    public void findAllTest() {
        List<DocumentType> documentTypes = documentTypeDAO.findAll();
        assertEquals(documentTypes.size(), 1);
    }


    @Test
    public void deleteTest() {
        DocumentType documenttype = entityManager.find(DocumentType.class, testDocumentType.getId());
        documentTypeDAO.deleteById(documenttype.getId());
        documenttype = entityManager.find(DocumentType.class, testDocumentType.getId());
        assertNull(documenttype);
    }
}
