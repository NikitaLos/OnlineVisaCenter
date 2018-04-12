package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface CountryDAO {
    void save(Country country) throws EntitySaveException;
    void delete(Country country) throws EntityDeleteException;
    Country find(Integer key) throws EntityFindException;
    void update(Country country) throws EntityUpdateException;
    List<Country> findAll(Class<Country> classType) throws EntityFindException;
    boolean isDuplicate(Country country) throws EntityFindException;
}
