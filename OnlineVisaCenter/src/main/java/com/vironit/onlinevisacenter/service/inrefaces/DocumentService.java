package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;

import java.util.List;

public interface DocumentService {
    void addDocument(DocumentType documentType) throws DuplicateException, DocumentServiceException;
    void deleteDocument(DocumentType documentType) throws DocumentServiceException;
    DocumentType getDocument(int key) throws DocumentServiceException;

    List<DocumentType> getAll() throws DocumentServiceException;

    void deleteDocumentById(Integer id) throws DocumentServiceException;
}
