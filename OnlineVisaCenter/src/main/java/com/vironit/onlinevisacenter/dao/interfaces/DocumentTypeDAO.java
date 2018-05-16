package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentTypeDAO {
    void save(DocumentType documentType) throws EntitySaveException;
    void delete(DocumentType documentType) throws EntityDeleteException;
    void deleteById(Integer id) throws EntityDeleteException;
    DocumentType find(Integer key) throws EntityFindException;
    void update(DocumentType documentType) throws EntityUpdateException;
    List<DocumentType> findAll(Class<DocumentType> classType) throws EntityFindException;
    void checkDuplicate(DocumentType documentType) throws EntityFindException, DuplicateException;

}
