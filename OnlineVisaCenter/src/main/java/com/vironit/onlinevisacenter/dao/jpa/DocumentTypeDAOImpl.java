package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DocumentTypeDAOImpl extends AbstractJPADAO<DocumentType,Integer> implements DocumentTypeDAO{

    public DocumentTypeDAOImpl() {
        super(DocumentType.class);
    }


    @Override
    public void checkDuplicate(DocumentType documentType) throws EntityFindException, DuplicateException {
        try {
            Query query = entityManager.createQuery("select d from DocumentType d where d.name = :name",DocumentType.class);
            List result = query.setParameter("name",documentType.getName()).getResultList();
            if (!result.isEmpty()){
                throw new DuplicateException("Such a document type already exists");
            }
        }catch (PersistenceException e){
            throw new EntityFindException(e);
        }
    }
}
