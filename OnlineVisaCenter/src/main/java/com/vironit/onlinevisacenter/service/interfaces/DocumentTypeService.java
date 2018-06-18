package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentTypeService {
    @Transactional
    void addDocument(DocumentType documentType) throws ServiceException;
    @Transactional
    void deleteDocumentById(Integer id) throws ServiceException;
    DocumentType getDocument(int key) throws ServiceException;
    List<DocumentType> getAll() throws ServiceException;

}
