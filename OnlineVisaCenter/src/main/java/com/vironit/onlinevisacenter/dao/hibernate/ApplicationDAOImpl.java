package com.vironit.onlinevisacenter.dao.hibernate;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class ApplicationDAOImpl extends AbstractJPADAO<Application,Integer> implements ApplicationDAO {

    public ApplicationDAOImpl(EntityManager entityManager) {
        super(entityManager, Application.class);
    }


    @Override
    public boolean isDuplicate(Application application) {
        return false;
    }

    @Override
    public List<Application> getApplicationsByClient(User user) {
        return entityManager.find(User.class,user.getId()).getApplications();
    }
}
