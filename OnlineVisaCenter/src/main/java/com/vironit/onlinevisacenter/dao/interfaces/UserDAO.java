package com.vironit.onlinevisacenter.dao.interfaces;


import com.vironit.onlinevisacenter.entity.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    void delete(User user);
    User getByPK(int key);
    void update(User user);
    List<User> getAll();
    boolean isDuplicate(User user);
    User getUserByLoginAndPassword(User user);
}
