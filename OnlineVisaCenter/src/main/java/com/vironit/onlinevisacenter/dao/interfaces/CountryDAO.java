package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;

import java.util.List;

public interface CountryDAO {
    void create(Country country);
    void delete(Country country);
    Country getByPK(int key);
    void update(Country country);
    List<Country> getAll();
    boolean isDuplicate(Country country);
}
