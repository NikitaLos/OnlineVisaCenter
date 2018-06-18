package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.util.List;

public interface CountryDAO {
    void save(Country country) throws DAOException;
    void delete(Country country) throws DAOException;
    Country find(Integer key) throws DAOException;
    void update(Country country) throws DAOException;
    List<Country> findAll(Class<Country> classType) throws DAOException;
    List findCountryByName(Country country) throws DAOException;
    void deleteById(Integer countryId) throws DAOException;
}
