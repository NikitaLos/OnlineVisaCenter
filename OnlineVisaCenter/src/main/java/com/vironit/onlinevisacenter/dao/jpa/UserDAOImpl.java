package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractJPADAO<User,Integer> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public boolean isDuplicate(User user) {
        Query query = entityManager.createQuery("select u from User u where u.login = :login or u.email=:email",User.class);
        List result = query
                .setParameter("login",user.getLogin())
                .setParameter("email",user.getEmail())
                .getResultList();
        return !result.isEmpty();

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

    @Override
    public List<User> findAllEmployees() throws EntityFindException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.role=:emp_role or u.role=:admin_role",User.class);
            return  query
                    .setParameter("emp_role", Role.EMPLOYEE)
                    .setParameter("admin_role",Role.ADMIN)
                    .getResultList();
        }catch (PersistenceException e){
            logger.error("find entity error",e);
            throw new EntityFindException(e);
        }
    }

}
