package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class CountryDAOImpl extends AbstractJPADAO<Country,Integer> implements CountryDAO {

    public CountryDAOImpl(EntityManager entityManager) {
        super(entityManager, Country.class);
    }

    @Override
    public boolean isDuplicate(Country country) throws EntityFindExeption {
        try {
            Query query = entityManager.createQuery("select c from Country c where c.name = :name",Country.class);
            List result = query.setParameter("name",country.getName()).getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindExeption(e);
        }
    }
}
