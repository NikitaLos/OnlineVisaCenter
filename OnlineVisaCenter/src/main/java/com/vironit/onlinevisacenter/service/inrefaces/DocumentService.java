package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.DocumentType;

public interface DocumentService {
    void addDocument(DocumentType documentType);
    void deleteDocument(DocumentType documentType);
    DocumentType getDocument(int key);
//    DocumentType getDocumentType(DocumentType document);
}
