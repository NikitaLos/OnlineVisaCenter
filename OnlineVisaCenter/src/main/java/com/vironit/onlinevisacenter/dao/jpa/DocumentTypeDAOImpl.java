package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class DocumentTypeDAOImpl extends AbstractJPADAO<DocumentType,Integer> implements DocumentTypeDAO{


    public DocumentTypeDAOImpl(EntityManager entityManager, Class<DocumentType> classType) {
        super(entityManager, classType);
    }


    @Override
    public boolean isDuplicate(DocumentType documentType) throws EntityFindExeption {
        try {
            Query query = entityManager.createQuery("select d from DocumentType d where d.name = :name",DocumentType.class);
            List result = query.setParameter("name",documentType.getName()).getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindExeption(e);
        }
    }
}
