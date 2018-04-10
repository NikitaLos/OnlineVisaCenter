package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface VisaDAO {
    void save(Visa visa) throws EntitySaveException;
    void delete(Visa visa) throws EntityDeleteException;
    Visa find(Integer key) throws EntityFindExeption;
    void update(Visa visa) throws EntityUpdateException;
    List<Visa> findAll(Class<Visa> classType) throws EntityFindExeption;
    boolean isDuplicate(Visa visa) throws EntityFindExeption;
}
