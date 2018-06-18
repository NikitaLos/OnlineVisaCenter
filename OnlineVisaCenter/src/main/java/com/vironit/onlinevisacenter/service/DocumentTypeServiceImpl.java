package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
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
    public void addDocument(DocumentType documentType) throws ServiceException {
        try {
            checkDuplicate(documentType);
            documentTypeDAO.save(documentType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public DocumentType getDocument(int key) throws ServiceException {
        try {
            return documentTypeDAO.find(key);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DocumentType> getAll() throws ServiceException {
        try {
            return documentTypeDAO.findAll(DocumentType.class);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteDocumentById(Integer id) throws ServiceException {
        try {
            documentTypeDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void checkDuplicate(DocumentType documentType) throws DAOException, ServiceException {
        if(!documentTypeDAO.findDocumentTypeByName(documentType).isEmpty()){
            throw new ServiceException("Such a document type already exists");
        }
    }
}
