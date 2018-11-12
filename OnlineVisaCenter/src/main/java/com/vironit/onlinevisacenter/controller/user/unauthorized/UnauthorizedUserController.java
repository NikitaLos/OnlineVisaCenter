package com.vironit.onlinevisacenter.controller.user.unauthorized;

import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.converter.UserConverter;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UnauthorizedUserController {
    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UnauthorizedUserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping(value = "/register")
    public void processRegister(@Valid @RequestBody UserDTO userDTO) throws ServiceException {
        User user = userConverter.convertToEntity(userDTO);
        userService.register(user);
    }
}
