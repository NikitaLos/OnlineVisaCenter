package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class VisaDAOImpl extends AbstractJPADAO<Visa,Integer> implements VisaDAO {

    public VisaDAOImpl() {
        super(Visa.class);
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

    @Override
    public List<Visa> findVisasByCountry(Country country) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select v from Visa v where v.country = :country");
            List result = query
                    .setParameter("country",country)
                    .getResultList();
            return result;
        }catch (PersistenceException e){
            logger.error("entity find error",e);
            throw new EntityFindException(e);
        }
    }
}
