package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.ServerLogger;
import com.vironit.onlinevisacenter.dao.interfaces.GenericDAO;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJPADAO<T,PK extends Serializable> implements GenericDAO<T,PK> {

    protected EntityManager entityManager;
    private Class<T> classType;
    protected ServerLogger logger;

    protected AbstractJPADAO(EntityManager entityManager, Class<T> classType) {
        this.entityManager = entityManager;
        this.classType = classType;
        this.logger = new ServerLogger(classType);
    }


    @Override
    public void save(T object) throws EntitySaveException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
        }catch (PersistenceException e){
            logger.error("save entity error",e);
            throw new EntitySaveException(e);
        }
    }

    @Override
    public T find(PK id) throws EntityFindExeption {
        try {
            return entityManager.find(classType,id);
        }catch (PersistenceException e){
            logger.error("find entity error",e);
            throw new EntityFindExeption(e);
        }
    }

    @Override
    public void update(T object) throws EntityUpdateException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(object);
            transaction.commit();
        }catch (PersistenceException e){
            logger.error("update entity error",e);
            throw new EntityUpdateException(e);
        }
    }

    @Override
    public void delete(T object) throws EntityDeleteException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(object);
            transaction.commit();
        }catch (PersistenceException e){
            logger.error("delete entity error",e);
            throw new EntityDeleteException(e);
        }
    }

    @Override
    public List<T> findAll(Class<T> classType) throws EntityFindExeption {
        try {
            return entityManager.createQuery("from "+ classType.getName()).getResultList();
        }catch (PersistenceException e){
            logger.error("findALL entity error",e);
            throw new EntityFindExeption(e);
        }
    }
}
