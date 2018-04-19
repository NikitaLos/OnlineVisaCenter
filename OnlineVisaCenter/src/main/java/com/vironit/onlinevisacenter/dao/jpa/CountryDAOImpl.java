package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Component
public class CountryDAOImpl extends AbstractJPADAO<Country,Integer> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

    @Override
    public boolean isDuplicate(Country country) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select c from Country c where c.name = :name",Country.class);
            List result = query.setParameter("name",country.getName()).getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindException(e);
        }
    }

    @Transactional
    @Override
    public void deleteByID(Integer id) throws EntityDeleteException {
        try {
            Query query = entityManager.createQuery("delete from Country c where c.id=:id");
            query.setParameter("id",id).executeUpdate();
        }catch (PersistenceException e){
            logger.error("delete entity error",e);
            throw new EntityDeleteException(e);
        }
    }
}
