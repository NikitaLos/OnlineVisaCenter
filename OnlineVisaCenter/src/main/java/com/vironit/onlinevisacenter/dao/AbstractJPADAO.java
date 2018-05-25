package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.dao.interfaces.GenericDAO;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
    public void save(T object) throws EntitySaveException {
        try {
            entityManager.persist(object);
        }catch (PersistenceException e){
            throw new EntitySaveException(e);
        }
    }

    @Override
    public T find(PK id) throws EntityFindException {
        try {
            return entityManager.find(classType,id);
        }catch (PersistenceException e){
            throw new EntityFindException(e);
        }
    }

    @Override
    public void update(T object) throws EntityUpdateException {
        try {
            entityManager.unwrap(Session.class).update(object);
        }catch (PersistenceException e){
            throw new EntityUpdateException(e);
        }
    }

    @Override
    public void delete(T object) throws EntityDeleteException {
        try {
            entityManager.remove(object);
        }catch (PersistenceException e){
            throw new EntityDeleteException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws EntityDeleteException {
        try {
            entityManager.remove(entityManager.find(classType,id));
        }catch (PersistenceException e){
            throw new EntityDeleteException(e);
        }
    }

    @Override
    public List<T> findAll(Class<T> classType) throws EntityFindException {
        try {
            return  entityManager.createQuery("from "+ classType.getName()).getResultList();
        }catch (PersistenceException e){
            throw new EntityFindException(e);
        }
    }
}
