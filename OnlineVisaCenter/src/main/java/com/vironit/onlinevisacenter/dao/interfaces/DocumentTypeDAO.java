package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.util.List;

public interface DocumentTypeDAO {
    void save(DocumentType documentType) throws DAOException;
    void delete(DocumentType documentType) throws DAOException;
    void deleteById(Integer id) throws DAOException;
    DocumentType find(Integer key) throws DAOException;
    void update(DocumentType documentType) throws DAOException;
    List<DocumentType> findAll(Class<DocumentType> classType) throws DAOException;
    List findDocumentTypeByName(DocumentType documentType) throws DAOException;
}
