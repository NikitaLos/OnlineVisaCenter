package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Message processRegister(@RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User user = userService.convertToEntity(userDTO);
        userService.register(user);
        return new Message("success");
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message duplicateUser(DuplicateException e) {
        return new Message(e.getMessage());
    }
}
