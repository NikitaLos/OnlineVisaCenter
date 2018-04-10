package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class ApplicationDAOImpl extends AbstractJPADAO<Application,Integer> implements ApplicationDAO {

    public ApplicationDAOImpl(EntityManager entityManager) {
        super(entityManager, Application.class);

    }

    @Override
    public List<Application> findApplicationsByClient(User user) throws EntityFindExeption {
        try {
            return entityManager.find(User.class,user.getId()).getApplications();
        }catch (PersistenceException e){
            logger.error("Find applications by client exception",e);
            throw new EntityFindExeption(e);
        }
    }
}
