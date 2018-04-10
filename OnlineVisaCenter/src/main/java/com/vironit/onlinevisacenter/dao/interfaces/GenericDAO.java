package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T,PK extends Serializable> {
    void save(T t) throws EntitySaveException;
    T find(PK id) throws EntityFindExeption;
    void update(T t) throws EntityUpdateException;
    void delete(T t) throws EntityDeleteException;
    List<T> findAll(Class<T> classType) throws EntityFindExeption;
}
