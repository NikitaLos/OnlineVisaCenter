package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.DocumentType;

import java.util.List;

public interface DocumentDAO {
    void create(DocumentType documentType);
    void delete(DocumentType documentType);
    DocumentType getByPK(int key);
    void update(DocumentType documentType);
    List<DocumentType> getAll();
    boolean isDuplicate(DocumentType documentType);
}
