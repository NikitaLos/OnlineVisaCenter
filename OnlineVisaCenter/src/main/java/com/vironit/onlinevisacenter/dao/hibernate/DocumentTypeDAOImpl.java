package com.vironit.onlinevisacenter.dao.hibernate;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DocumentTypeDAOImpl extends AbstractJPADAO<DocumentType,Integer> implements DocumentTypeDAO{


    public DocumentTypeDAOImpl(EntityManager entityManager, Class<DocumentType> classType) {
        super(entityManager, classType);
    }


    @Override
    public boolean isDuplicate(DocumentType documentType) {
        Query query = entityManager.createQuery("select d from DocumentType d where :name=?",DocumentType.class);
        Object document = query.setParameter("name",documentType.getName()).getSingleResult();
        return document!=null;
    }
}
