package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;

import java.util.List;

public interface UserDAO {
    void save(User user) throws EntitySaveException;
    void delete(User user) throws EntityDeleteException;
    User find(Integer key) throws EntityFindException;
    void update(User user) throws EntityUpdateException;
    List<User> findAll(Class<User> classType) throws EntityFindException;
    void checkDuplicate(User user) throws DuplicateException;
    User findUserByLoginAndPassword(User user) throws EntityFindException;
    List<User> findAllEmployees() throws EntityFindException;
    void deleteById(Integer id) throws EntityDeleteException;
    User findUserByLogin(String login) throws EntityFindException;
}
