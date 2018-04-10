package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface UserDAO {
    void save(User user) throws EntitySaveException;
    void delete(User user) throws EntityDeleteException;
    User find(Integer key) throws EntityFindExeption;
    void update(User user) throws EntityUpdateException;
    List<User> findAll(Class<User> classType) throws EntityFindExeption;
    boolean isDuplicate(User user) throws EntityFindExeption;
    User getUserByLoginAndPassword(User user) throws EntityFindExeption;
}
