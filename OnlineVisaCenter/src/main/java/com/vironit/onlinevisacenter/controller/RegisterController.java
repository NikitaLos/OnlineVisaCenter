package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegisterForm(){
        return "registration";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegister(User user, Model model) throws UserServiceException {
        try {
            userService.register(user);
            return "forward:/login";
        } catch (DuplicateException e) {
            model.addAttribute("duplicate_message",e.getMessage());
            return "registration";
        }
    }
}
