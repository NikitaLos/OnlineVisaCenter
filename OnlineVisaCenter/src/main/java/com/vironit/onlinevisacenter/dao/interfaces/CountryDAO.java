package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface CountryDAO {
    void save(Country country) throws EntitySaveException;
    void delete(Country country) throws EntityDeleteException;
    Country find(Integer key) throws EntityFindExeption;
    void update(Country country) throws EntityUpdateException;
    List<Country> findAll(Class<Country> classType) throws EntityFindExeption;
    boolean isDuplicate(Country country) throws EntityFindExeption;
}
