package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface VisaDAO {
    void save(Visa visa) throws EntitySaveException;
    void delete(Visa visa) throws EntityDeleteException;
    void deleteById(Integer id) throws EntityDeleteException;
    Visa find(Integer key) throws EntityFindException;
    void update(Visa visa) throws EntityUpdateException;
    List<Visa> findAll(Class<Visa> classType) throws EntityFindException;
    boolean isDuplicate(Visa visa) throws EntityFindException;

    List<Visa> findVisasByCountry(Country country) throws EntityFindException;
}
