package com.vironit.onlinevisacenter.service.inrefaces;


import com.vironit.onlinevisacenter.entity.User;

public interface UserService {
    void register(User user);
    void deleteUser(User user);
    User logIn(User user);
    void logOut(User user);

}
