package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class UserDAOImpl extends AbstractJPADAO<User,Integer> implements UserDAO {

    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public boolean isDuplicate(User user) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :login or u.email=:email",User.class);
            List result = query
                    .setParameter("login",user.getLogin())
                    .setParameter("email",user.getEmail())
                    .getResultList();
            return !result.isEmpty();
        }catch (PersistenceException e){
            logger.error("checking duplicate error",e);
            throw new EntityFindException(e);
        }
    }

    @Override
    public User getUserByLoginAndPassword(User user) throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :login and u.password=:password",User.class);
            return (User) query
                    .setParameter("login",user.getLogin())
                    .setParameter("password",user.getPassword())
                    .getSingleResult();
        }catch (PersistenceException e){
            logger.error("find entity error",e);
            throw new EntityFindException(e);
        }
    }
}
