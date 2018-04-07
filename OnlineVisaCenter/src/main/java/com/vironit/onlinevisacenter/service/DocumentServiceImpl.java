package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentDAO;
import com.vironit.onlinevisacenter.entity.Document;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;

public class DocumentServiceImpl implements DocumentService {

    DocumentDAO documentDAO;

    public DocumentServiceImpl(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public void addDocument(Document document) {
        if(!documentDAO.isDuplicate(document)){
            documentDAO.create(document);
        }else {
            // todo exception
        }
    }

    @Override
    public void deleteDocument(Document document) {
        documentDAO.delete(document);
    }

    @Override
    public Document getDocument(int key) {
        return documentDAO.getByPK(key);
    }

}
