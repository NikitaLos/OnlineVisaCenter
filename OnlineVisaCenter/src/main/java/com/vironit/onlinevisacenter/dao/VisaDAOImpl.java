package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class VisaDAOImpl extends AbstractJPADAO<Visa,Integer> implements VisaDAO {

    public VisaDAOImpl() {
        super(Visa.class);
    }

    @Override
    public void checkDuplicate(Visa visa) throws EntityFindException, DuplicateException {
        try {
            Query query = entityManager.createQuery("select v from Visa v where v.type = :type and " +
                    "v.country.name= :countryName");
            List result = query
                    .setParameter("type",visa.getType())
                    .setParameter("countryName",visa.getCountry().getName())
                    .getResultList();
            if(!result.isEmpty()){
                throw new DuplicateException("such a visa already exists");
            }
        }catch (PersistenceException e){
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
            throw new EntityFindException(e);
        }
    }
}
