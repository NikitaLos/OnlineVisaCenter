package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentTypeDAO documentTypeDAO;

    @Autowired
    public DocumentServiceImpl(DocumentTypeDAO documentTypeDAO) {
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
    public void deleteDocument(DocumentType documentType) throws DocumentServiceException {
        try {
            documentTypeDAO.delete(documentType);
        } catch (EntityDeleteException e) {
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

    @Override
    public DocumentType convertToEntity(DocumentTypeDTO documentTypeDTO){
        DocumentType documentType = new DocumentType();
        documentType.setName(documentTypeDTO.getName());
        documentType.setId(documentTypeDTO.getId());
        return documentType;
    }

    @Override
    public DocumentTypeDTO convertToDTO(DocumentType documentType){
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        documentTypeDTO.setName(documentType.getName());
        documentTypeDTO.setId(documentType.getId());
        return documentTypeDTO;
    }

}
