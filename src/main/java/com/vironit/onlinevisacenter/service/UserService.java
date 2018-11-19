package com.vironit.onlinevisacenter.service;


import com.vironit.onlinevisacenter.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    void register(UserDTO userDTO);

    void deleteUserById(Integer id);

    UserDTO logIn(UserDTO userDTO);

    List<UserDTO> findAllEmployees();

    UserDTO getUser(Integer id);

    UserDTO getUserByLogin(String login);

}
