package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;

import java.util.List;

public interface CountryDAO {
    void save(Country country);
    void delete(Country country);
    Country find(Integer key);
    void update(Country country);
    List<Country> findAll(Class<Country> classType);
    boolean isDuplicate(Country country);
}
