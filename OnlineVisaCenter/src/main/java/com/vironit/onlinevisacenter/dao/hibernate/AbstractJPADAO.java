package com.vironit.onlinevisacenter.dao.hibernate;

import com.vironit.onlinevisacenter.dao.interfaces.GenericDAO;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJPADAO<T,PK extends Serializable> implements GenericDAO<T,PK> {

    protected EntityManager entityManager;
    private Class<T> classType;

    public AbstractJPADAO(EntityManager entityManager, Class<T> classType) {
        this.entityManager = entityManager;
        this.classType = classType;
    }

    @Override
    public void save(T object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public T find(PK id) {
        return entityManager.find(classType,id);
    }

    @Override
    public void update(T object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> findAll(Class<T> classType) {
        return entityManager.createQuery("from "+ classType.getName()).getResultList();
    }
}
