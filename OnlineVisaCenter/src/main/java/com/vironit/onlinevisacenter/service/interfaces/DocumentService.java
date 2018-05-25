package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentService {
    @Transactional
    void addDocument(DocumentType documentType) throws DuplicateException, DocumentServiceException;
    @Transactional
    void deleteDocumentById(Integer id) throws DocumentServiceException;
    DocumentType getDocument(int key) throws DocumentServiceException;
    List<DocumentType> getAll() throws DocumentServiceException;
}
