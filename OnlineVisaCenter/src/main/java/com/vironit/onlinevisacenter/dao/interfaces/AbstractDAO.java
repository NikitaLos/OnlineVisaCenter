package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.Identified;

import java.util.List;

public interface AbstractDAO<T> {
    void create(T object);
    void delete(T object);
    T getByPK(T object);
    void update(T object);
    List<T> getAll();
    boolean isDuplicate(T object);
}
