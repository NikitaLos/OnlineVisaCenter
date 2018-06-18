package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void addDocumentTest() throws ServiceException, DAOException {
        DocumentType documentType = entityHelper.prepareDocumentType();
        documentTypeService.addDocument(documentType);
        verify(documentTypeDAO,times(1)).save(documentType);
        verify(documentTypeDAO,times(1)).findDocumentTypeByName(documentType);
    }

    @Test
    public void getDocumentTest() throws DAOException, ServiceException {
        DocumentType documentType = entityHelper.prepareDocumentType();
        when(documentTypeDAO.find(1)).thenReturn(documentType);
        assertEquals(documentType,documentTypeService.getDocument(1));
    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        DocumentType[] documentTypes = {entityHelper.prepareDocumentType(),entityHelper.prepareDocumentType()};
        when(documentTypeDAO.findAll(DocumentType.class)).thenReturn(Arrays.asList(documentTypes));
        assertEquals(2,documentTypeService.getAll().size());
    }

    @Test
    public void deleteDocumentByIdTest() throws ServiceException, DAOException {
        documentTypeService.deleteDocumentById(1);
        verify(documentTypeDAO,times(1)).deleteById(1);

    }

    @Test(expected = ServiceException.class)
    public void isDuplicate() throws ServiceException, DAOException {
        DocumentType documentType = entityHelper.prepareDocumentType();
        List<DocumentType> expectedList = new ArrayList<>();
        expectedList.add(documentType);
        when(documentTypeDAO.findDocumentTypeByName(documentType)).thenReturn(expectedList);
        documentTypeService.addDocument(documentType);
    }
}
