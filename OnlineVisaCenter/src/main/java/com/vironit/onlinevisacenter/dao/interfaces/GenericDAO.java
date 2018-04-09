package com.vironit.onlinevisacenter.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T,PK extends Serializable> {
    void save(T t);
    T find(PK id);
    void update(T t);
    void delete(T t);
    List<T> findAll(Class<T> classType);
}
