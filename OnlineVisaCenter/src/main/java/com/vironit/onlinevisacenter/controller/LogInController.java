package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LogInController {

    private UserService userService;

    @Autowired
    public LogInController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public UserDTO processLogin(@RequestBody UserDTO userDTO, HttpSession session) throws LoginationException {
        User user = userService.convertToEntity(userDTO);
        User authorizedUser = userService.logIn(user);
        session.setAttribute("user_id",authorizedUser.getId());
        return userService.convertToDTO(authorizedUser);
    }

    @ExceptionHandler(LoginationException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message userNotExist(LoginationException e) {
        return new Message(e.getMessage());
    }
}
