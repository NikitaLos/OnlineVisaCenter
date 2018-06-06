package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentServiceTest extends BaseServiceTest {
    @Autowired
    private DocumentTypeService documentTypeService;

    @MockBean
    private DocumentTypeDAO documentTypeDAO;

    @Test
    public void addDocumentTest() throws DuplicateException, DocumentServiceException, EntitySaveException, EntityFindException {
        DocumentType documentType = entityHelper.prepareDocumentType();
        documentTypeService.addDocument(documentType);
        verify(documentTypeDAO,times(1)).save(documentType);
        verify(documentTypeDAO,times(1)).checkDuplicate(documentType);
    }

    @Test
    public void getDocumentTest() throws EntityFindException, DocumentServiceException {
        DocumentType documentType = entityHelper.prepareDocumentType();
        when(documentTypeDAO.find(1)).thenReturn(documentType);
        assertEquals(documentType,documentTypeService.getDocument(1));
    }

    @Test
    public void getAllTest() throws DocumentServiceException, EntityFindException {
        DocumentType[] documentTypes = {entityHelper.prepareDocumentType(),entityHelper.prepareDocumentType()};
        when(documentTypeDAO.findAll(DocumentType.class)).thenReturn(Arrays.asList(documentTypes));
        assertEquals(2,documentTypeService.getAll().size());
    }

    @Test
    public void deleteDocumentByIdTest() throws DocumentServiceException, EntityDeleteException {
        documentTypeService.deleteDocumentById(1);
        verify(documentTypeDAO,times(1)).deleteById(1);

    }
}
