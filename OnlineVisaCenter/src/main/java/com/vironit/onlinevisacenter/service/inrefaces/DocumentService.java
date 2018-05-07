package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentService {
    @Transactional
    void addDocument(DocumentType documentType) throws DuplicateException, DocumentServiceException;

    @Transactional
    void deleteDocument(DocumentType documentType) throws DocumentServiceException;

    @Transactional
    void deleteDocumentById(Integer id) throws DocumentServiceException;

    DocumentType getDocument(int key) throws DocumentServiceException;

    List<DocumentType> getAll() throws DocumentServiceException;

    DocumentType convertToEntity(DocumentTypeDTO documentTypeDTO);

    DocumentTypeDTO convertToDTO(DocumentType documentType);
}
