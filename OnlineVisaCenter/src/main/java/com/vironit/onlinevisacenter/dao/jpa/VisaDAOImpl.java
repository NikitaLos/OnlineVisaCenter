package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class VisaDAOImpl extends AbstractJPADAO<Visa,Integer> implements VisaDAO {
    public VisaDAOImpl(EntityManager entityManager) {
        super(entityManager, Visa.class);
    }

    @Override
    public boolean isDuplicate(Visa visa) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select v from Visa v where v.type = :type and " +
                    "v.country.name= :countryName");
            List result = query
                    .setParameter("type",visa.getType())
                    .setParameter("countryName",visa.getCountry().getName())
                    .getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindException(e);
        }
    }
}
