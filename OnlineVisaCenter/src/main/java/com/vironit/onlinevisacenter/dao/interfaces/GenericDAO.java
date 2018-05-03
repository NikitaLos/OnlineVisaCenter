package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T,PK extends Serializable> {
    void save(T t) throws EntitySaveException;
    void update(T t) throws EntityUpdateException;
    void delete(T t) throws EntityDeleteException;
    void deleteById(Integer id) throws EntityDeleteException;
    T find(PK id) throws EntityFindException;
    List<T> findAll(Class<T> classType) throws EntityFindException;
}
