package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void processRegister(@RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User user = userService.convertToEntity(userDTO);
        userService.register(user);
    }
}
