package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDAOImpl extends AbstractJPADAO<Country,Integer> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

    @Override
    public void checkDuplicate(Country country) throws EntityFindException, DuplicateException {
        try {
            Query query = entityManager.createQuery("select c from Country c where c.name = :name",Country.class);
            List result = query.setParameter("name",country.getName()).getResultList();
            if (!result.isEmpty()){
                throw new DuplicateException("Such a country already exists");
            }
        }catch (PersistenceException e){
            throw new EntityFindException(e);
        }
    }
}
