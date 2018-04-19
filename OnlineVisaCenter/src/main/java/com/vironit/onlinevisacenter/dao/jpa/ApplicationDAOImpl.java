package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
@Component
public class ApplicationDAOImpl extends AbstractJPADAO<Application,Integer> implements ApplicationDAO {

    public ApplicationDAOImpl() {
        super(Application.class);

    }

    @Override
    public List<Application> findApplicationsByClient(User user) throws EntityFindException {
        try {
            return entityManager.find(User.class,user.getId()).getApplications();
        }catch (PersistenceException e){
            logger.error("Find applications by client exception",e);
            throw new EntityFindException(e);
        }
    }
}
