package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.Visa;

import java.util.List;

public interface VisaDAO {
    void save(Visa visa);
    void delete(Visa visa);
    Visa find(Integer key);
    void update(Visa visa);
    List<Visa> findAll(Class<Visa> classType);
    boolean isDuplicate(Visa visa);
}
