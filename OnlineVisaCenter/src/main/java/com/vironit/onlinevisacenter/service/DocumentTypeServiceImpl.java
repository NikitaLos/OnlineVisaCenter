package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.repository.jpa.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private DocumentTypeDAO documentTypeDAO;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDAO documentTypeDAO) {
        this.documentTypeDAO = documentTypeDAO;
    }

    @Override
    public void addDocument(DocumentType documentType) throws ServiceException {
        checkDuplicate(documentType);
        documentTypeDAO.save(documentType);
    }

    @Override
    public DocumentType getDocument(int key) throws ServiceException {
        return documentTypeDAO.findById(key).orElseThrow(ServiceException::new);
    }

    @Override
    public List<DocumentType> getAll(){
        return documentTypeDAO.findAll();
    }

    @Override
    public void deleteDocumentById(Integer id) {
        documentTypeDAO.deleteById(id);
    }

    private void checkDuplicate(DocumentType documentType) throws ServiceException {
        if (!documentTypeDAO.findByName(documentType.getName()).isEmpty()) {
            throw new ServiceException("Such a document type already exists");
        }
    }
}
