package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.User;

import java.util.List;

public interface ApplicationDAO {
    void save(Application application);
    void delete(Application application);
    Application find(Integer key);
    void update(Application application);
    List<Application> findAll(Class<Application> classType);
    boolean isDuplicate(Application application);
    List<Application> getApplicationsByClient(User user);

}
