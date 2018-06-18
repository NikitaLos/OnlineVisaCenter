package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.GenericDAO;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJPADAO<T,PK extends Serializable> implements GenericDAO<T,PK> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> classType;

    AbstractJPADAO(Class<T> classType) {
        this.classType = classType;
    }


    @Override
    public void save(T object) throws DAOException {
        try {
            entityManager.persist(object);
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public T find(PK id) throws DAOException {
        try {
            return entityManager.find(classType,id);
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public void update(T object) throws DAOException {
        try {
            entityManager.unwrap(Session.class).update(object);
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(T object) throws DAOException {
        try {
            entityManager.remove(object);
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DAOException {
        try {
            entityManager.remove(entityManager.find(classType,id));
        }catch (Exception e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<T> findAll(Class<T> classType) throws DAOException {
        try {
            return  entityManager.createQuery("from "+ classType.getName()).getResultList();
        }catch (Exception e){
            throw new DAOException(e);
        }
    }
}
