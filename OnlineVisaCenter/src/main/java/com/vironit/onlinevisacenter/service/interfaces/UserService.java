package com.vironit.onlinevisacenter.service.interfaces;


import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    void register(User user) throws DuplicateException, UserServiceException;
    @Transactional
    void deleteUserById(Integer id) throws UserServiceException;
    User logIn(User user) throws LoginationException;
    List<User> findAllEmployees() throws UserServiceException;
    User getUser(Integer id) throws UserServiceException;
    User convertToEntity(UserDTO userDTO);
    UserDTO convertToDTO(User user);

    User getUserByLogin(String login) throws UserServiceException;
}
