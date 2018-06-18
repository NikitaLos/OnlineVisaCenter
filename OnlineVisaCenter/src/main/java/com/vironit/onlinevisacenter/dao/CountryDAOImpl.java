package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDAOImpl extends AbstractJPADAO<Country,Integer> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

    @Override
    public List<Country> findCountryByName(Country country) throws DAOException {
        try {
            Query query = entityManager.createQuery("select c from Country c where c.name = :name", Country.class);
            return query.setParameter("name", country.getName()).getResultList();
        }catch (Exception e){
            throw  new DAOException(e);
        }
    }
}
