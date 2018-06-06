package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private DocumentTypeDAO documentTypeDAO;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDAO documentTypeDAO) {
        this.documentTypeDAO = documentTypeDAO;
    }

    @Override
    public void addDocument(DocumentType documentType) throws DuplicateException, DocumentServiceException {
        try {
            documentTypeDAO.checkDuplicate(documentType);
            documentTypeDAO.save(documentType);
        } catch (EntityFindException | EntitySaveException e) {
            throw new DocumentServiceException(e);
        }
    }

    @Override
    public DocumentType getDocument(int key) throws DocumentServiceException {
        try {
            return documentTypeDAO.find(key);
        } catch (EntityFindException e) {
            throw new DocumentServiceException(e);
        }
    }

    @Override
    public List<DocumentType> getAll() throws DocumentServiceException {
        try {
            return documentTypeDAO.findAll(DocumentType.class);
        } catch (EntityFindException e) {
            throw new DocumentServiceException(e);
        }
    }

    @Override
    public void deleteDocumentById(Integer id) throws DocumentServiceException {
        try {
            documentTypeDAO.deleteById(id);
        } catch (EntityDeleteException e) {
            throw new DocumentServiceException(e);
        }
    }
}
