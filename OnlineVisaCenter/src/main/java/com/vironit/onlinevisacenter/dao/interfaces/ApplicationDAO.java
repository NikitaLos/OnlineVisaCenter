package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface ApplicationDAO {
    void save(Application application) throws EntitySaveException;
    void delete(Application application) throws EntityDeleteException;
    Application find(Integer key) throws EntityFindExeption;
    void update(Application application) throws EntityUpdateException;
    List<Application> findAll(Class<Application> classType) throws EntityFindExeption;
    List<Application> findApplicationsByClient(User user) throws EntityFindExeption;

}
