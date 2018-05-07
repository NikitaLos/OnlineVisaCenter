package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
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
    public boolean isDuplicate(DocumentType documentType) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select d from DocumentType d where d.name = :name",DocumentType.class);
            List result = query.setParameter("name",documentType.getName()).getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindException(e);
        }
    }
}
