package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    void delete(User user);
    User find(Integer key);
    void update(User user);
    List<User> findAll(Class<User> classType);
    boolean isDuplicate(User user);
    User getUserByLoginAndPassword(User user);
}
