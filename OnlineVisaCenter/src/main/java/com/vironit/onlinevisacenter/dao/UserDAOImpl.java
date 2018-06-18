package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractJPADAO<User,Integer> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public List<User> findUserByLoginOrEmail(User user) throws  DAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :login or u.email=:email",User.class);
            return query
                    .setParameter("login",user.getLogin())
                    .setParameter("email",user.getEmail())
                    .getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }


    }

    @Override
    public User findUserByLoginAndPassword(User user) throws DAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :login and u.password=:password",User.class);
            return (User) query
                    .setParameter("login",user.getLogin())
                    .setParameter("password",user.getPassword())
                    .getSingleResult();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public User findUserByLogin(String login) throws DAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :login",User.class);
            return (User) query
                    .setParameter("login",login)
                    .getSingleResult();
        }catch (Exception e){
            throw new DAOException(e);
        }

    }

    @Override
    public List<User> findAllEmployees() throws DAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.role=:emp_role or u.role=:admin_role",User.class);
            return  query
                    .setParameter("emp_role", Role.EMPLOYEE)
                    .setParameter("admin_role",Role.ADMIN)
                    .getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

}
