package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Document;

public interface DocumentService {
    void addDocument(Document document);
    void deleteDocument(Document document);
    Document getDocument(int key);
//    Document getDocument(Document document);
}
