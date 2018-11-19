package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.AbstractTest;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeDTOToDocumentTypeConverter;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeToDocumentTypeDTOConverter;
import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.repository.jpa.DocumentTypeDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentServiceTest extends AbstractTest {
    @Autowired
    private DocumentTypeService documentTypeService;

    @MockBean
    private DocumentTypeToDocumentTypeDTOConverter toDocumentTypeDTOConverter;

    @MockBean
    private DocumentTypeDTOToDocumentTypeConverter toDocumentTypeConverter;

    @MockBean
    private DocumentTypeDAO documentTypeDAO;

    @Mock
    private DocumentType documentType;

    @Mock
    private DocumentTypeDTO documentTypeDTO;

    @Before
    public void setUp(){
        when(toDocumentTypeConverter.convert(documentTypeDTO)).thenReturn(documentType);
        when(toDocumentTypeDTOConverter.convert(documentType)).thenReturn(documentTypeDTO);
        when(documentType.getId()).thenReturn(1);
        when(documentType.getName()).thenReturn("Test Document Type");
    }

    @Test
    public void getCountryTest() {
        when(documentTypeDAO.findById(1)).thenReturn(Optional.of(documentType));
        assertEquals(documentTypeDTO,documentTypeService.getDocument(1));
    }

    @Test
    public void addDocumentTest() {
        documentTypeService.addDocument(documentTypeDTO);
        verify(documentTypeDAO, times(1)).save(documentType);
        verify(documentTypeDAO, times(1)).findByName(documentType.getName());
    }

    @Test
    public void getAllTest() {
        DocumentType[] documentTypes = {documentType, documentType};
        when(documentTypeDAO.findAll()).thenReturn(Arrays.asList(documentTypes));
        assertEquals(2, documentTypeService.getAll().size());
    }

    @Test
    public void deleteDocumentByIdTest() {
        documentTypeService.deleteDocumentById(1);
        verify(documentTypeDAO, times(1)).deleteById(1);

    }

    @Test(expected = DuplicateException.class)
    public void isDuplicate() {
        List<DocumentType> expectedList = new ArrayList<>();
        expectedList.add(documentType);
        when(documentTypeDAO.findByName(documentType.getName())).thenReturn(expectedList);
        documentTypeService.addDocument(documentTypeDTO);
    }
}
