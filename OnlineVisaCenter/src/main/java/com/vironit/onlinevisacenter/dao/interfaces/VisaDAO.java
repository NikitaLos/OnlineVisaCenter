package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;

import java.util.List;

public interface VisaDAO {
    void create(Visa visa);
    void delete(Visa visa);
    Visa getByPK(int key);
    void update(Visa visa);
    List<Visa> getAll();
    boolean isDuplicate(Visa visa);
}
