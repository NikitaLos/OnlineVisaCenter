package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class VisaDAOImpl extends AbstractJPADAO<Visa,Integer> implements VisaDAO {

    public VisaDAOImpl() {
        super(Visa.class);
    }

    @Override
    public List<Visa> findVisaByType(Visa visa) throws DAOException {
        try {
            Query query = entityManager.createQuery("select v from Visa v where v.type = :type and " +
                    "v.country.name= :countryName");
            return query
                    .setParameter("type",visa.getType())
                    .setParameter("countryName",visa.getCountry().getName())
                    .getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Visa> findVisasByCountry(Country country) throws DAOException {
        try {
            Query query = entityManager.createQuery("select v from Visa v where v.country = :country");
            return query
                    .setParameter("country",country)
                    .getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }
}
