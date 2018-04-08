package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;

public class DocumentServiceImpl implements DocumentService {

    DocumentDAO documentDAO;

    public DocumentServiceImpl(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public void addDocument(DocumentType documentType) {
        if(!documentDAO.isDuplicate(documentType)){
            documentDAO.create(documentType);
        }else {
            // todo exception
        }
    }

    @Override
    public void deleteDocument(DocumentType documentType) {
        documentDAO.delete(documentType);
    }

    @Override
    public DocumentType getDocument(int key) {
        return documentDAO.getByPK(key);
    }

}
