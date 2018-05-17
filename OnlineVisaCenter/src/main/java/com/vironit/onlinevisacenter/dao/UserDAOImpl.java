package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractJPADAO<User,Integer> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public void checkDuplicate(User user) throws DuplicateException {
        Query query = entityManager.createQuery("select u from User u where u.login = :login or u.email=:email",User.class);
        List result = query
                .setParameter("login",user.getLogin())
                .setParameter("email",user.getEmail())
                .getResultList();
        if (!result.isEmpty()){
            throw new DuplicateException("Such login or email already exists");
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
            throw new EntityFindException(e);
        }
    }

}
