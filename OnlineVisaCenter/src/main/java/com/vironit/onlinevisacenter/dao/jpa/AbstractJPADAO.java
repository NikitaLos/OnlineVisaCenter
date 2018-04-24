package com.vironit.onlinevisacenter.dao.jpa;

import com.vironit.onlinevisacenter.ServerLogger;
import com.vironit.onlinevisacenter.dao.interfaces.GenericDAO;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJPADAO<T,PK extends Serializable> implements GenericDAO<T,PK> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> classType;
    protected ServerLogger logger;

    AbstractJPADAO(Class<T> classType) {
        logger = new ServerLogger(classType);
        this.classType = classType;
    }


    @Transactional
    @Override
    public void save(T object) throws EntitySaveException {
        try {
            entityManager.persist(object);
        }catch (PersistenceException e){
            logger.error("save entity error",e);
            throw new EntitySaveException(e);
        }
    }

    @Override
    public T find(PK id) throws EntityFindException {
        try {
            return entityManager.find(classType,id);
        }catch (PersistenceException e){
            logger.error("find entity error",e);
            throw new EntityFindException(e);
        }
    }

    @Transactional
    @Override
    public void update(T object) throws EntityUpdateException {
        try {
            entityManager.merge(object);
        }catch (PersistenceException e){
            logger.error("update entity error",e);
            throw new EntityUpdateException(e);
        }
    }

    @Transactional
    @Override
    public void delete(T object) throws EntityDeleteException {
        try {
            entityManager.remove(object);
        }catch (PersistenceException e){
            logger.error("delete entity error",e);
            throw new EntityDeleteException(e);
        }
    }

    @Transactional
    @Override
    public void deleteById(Integer id) throws EntityDeleteException {
        try {
            entityManager.remove(entityManager.find(classType,id));
        }catch (PersistenceException e){
            logger.error("delete entity error",e);
            throw new EntityDeleteException(e);
        }
    }

    @Override
    public List<T> findAll(Class<T> classType) throws EntityFindException {
        try {
            return  entityManager.createQuery("from "+ classType.getName()).getResultList();
        }catch (PersistenceException e){
            logger.error("findAll entity error",e);
            throw new EntityFindException(e);
        }
    }
}
