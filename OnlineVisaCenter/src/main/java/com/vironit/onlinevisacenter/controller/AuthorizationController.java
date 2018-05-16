package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.ResponseExceptionDTO;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AuthorizationController {

    private UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void processRegister(@Valid @RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User user = userService.convertToEntity(userDTO);
        userService.register(user);
    }

    @PostMapping(value = "/login")
    public UserDTO processLogin(@RequestBody UserDTO userDTO, HttpSession session) throws LoginationException {
        User user = userService.convertToEntity(userDTO);
        User authorizedUser = userService.logIn(user);
        session.setAttribute("user_id",authorizedUser.getId());
        return userService.convertToDTO(authorizedUser);
    }

    @GetMapping(value = "/logout")
    public void logOut(HttpSession session)  {
        session.invalidate();
    }

    @ExceptionHandler(LoginationException.class)
    public ResponseEntity<ResponseExceptionDTO> userNotExist(LoginationException e) {
        return new ResponseEntity<>(new ResponseExceptionDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
