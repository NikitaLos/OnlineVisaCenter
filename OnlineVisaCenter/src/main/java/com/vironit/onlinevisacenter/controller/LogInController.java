package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LogInController {

    private UserService userService;

    @Autowired
    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLogin(User user, Model model, HttpSession session) {
        try {
            User authorizedUser = userService.logIn(user);
            session.setAttribute("user_id",user.getId());
            if (authorizedUser.getRole()==Role.ADMIN){
                return "redirect:/admin";
            }
            if (authorizedUser.getRole()==Role.EMPLOYEE){
                return "redirect:/employee";
            }
            return "main_page";
        } catch (LoginationException e) {
            model.addAttribute("not_register_message",e.getMessage());
            return "login";
        }

    }
}
