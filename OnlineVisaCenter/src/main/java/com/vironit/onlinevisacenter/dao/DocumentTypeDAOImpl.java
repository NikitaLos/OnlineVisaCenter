package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DocumentTypeDAOImpl extends AbstractJPADAO<DocumentType,Integer> implements DocumentTypeDAO{

    public DocumentTypeDAOImpl() {
        super(DocumentType.class);
    }


    @Override
    public List<DocumentType> findDocumentTypeByName(DocumentType documentType) throws DAOException {
        try {
            Query query = entityManager.createQuery("select d from DocumentType d where d.name = :name",DocumentType.class);
            return query.setParameter("name",documentType.getName()).getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }
}
