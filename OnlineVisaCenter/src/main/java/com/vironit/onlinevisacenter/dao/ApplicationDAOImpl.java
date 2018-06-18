package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ApplicationDAOImpl extends AbstractJPADAO<Application,Integer> implements ApplicationDAO {

    public ApplicationDAOImpl() {
        super(Application.class);
    }

    @Override
    public List<Application> findApplicationsByClient(Integer userId) throws DAOException {
        try {
            Query query = entityManager.createQuery("select a from Application a where a.user.id = :userId",Application.class);
            return query.setParameter("userId",userId).getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Application> findAll(Class<Application> classType) throws DAOException {
        try {
            return  entityManager.createQuery("select a from Application a order by a.creationTime desc",Application.class).getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }
}
