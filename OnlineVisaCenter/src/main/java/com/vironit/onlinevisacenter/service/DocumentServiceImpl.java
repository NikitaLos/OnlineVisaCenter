package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentServiceImpl implements DocumentService {

    DocumentTypeDAO documentTypeDAO;

    @Autowired
    public DocumentServiceImpl(DocumentTypeDAO documentTypeDAO) {
        this.documentTypeDAO = documentTypeDAO;
    }

    @Override
    public void addDocument(DocumentType documentType) {
//        if(!documentTypeDAO.isDuplicate(documentType)){
//            documentTypeDAO.save(documentType);
//        }else {
//            // todo exception
//        }
    }

    @Override
    public void deleteDocument(DocumentType documentType) {
//        documentTypeDAO.delete(documentType);
    }

    @Override
    public DocumentType getDocument(int key) {
//        return documentTypeDAO.find(key);
        return null;
    }

}
