package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.DocumentType;

import java.util.List;

public interface DocumentTypeDAO {
    void save(DocumentType documentType);
    void delete(DocumentType documentType);
    DocumentType find(Integer key);
    void update(DocumentType documentType);
    List<DocumentType> findAll(Class<DocumentType> classType);
    boolean isDuplicate(DocumentType documentType);
}
