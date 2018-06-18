package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DAOException;

import java.util.List;

public interface VisaDAO {
    void save(Visa visa) throws DAOException;
    void delete(Visa visa) throws DAOException;
    void deleteById(Integer id) throws DAOException;
    Visa find(Integer key) throws DAOException;
    void update(Visa visa) throws DAOException;
    List<Visa> findAll(Class<Visa> classType) throws DAOException;
    List findVisaByType(Visa visa) throws DAOException;
    List<Visa> findVisasByCountry(Country country) throws DAOException;
}
