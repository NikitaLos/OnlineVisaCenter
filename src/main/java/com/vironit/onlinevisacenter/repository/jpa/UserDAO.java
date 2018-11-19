package com.vironit.onlinevisacenter.repository.jpa;


import com.vironit.onlinevisacenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    List<User> findByLoginOrEmail(String login, String email);
    User findByLoginAndPassword(String login, String password);
    @Query("select u from User u where u.role='EMPLOYEE' or u.role='ADMIN'")
    List<User> findAllEmployees();
    User findByLogin(String login);
}
