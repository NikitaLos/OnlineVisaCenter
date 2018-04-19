package com.vironit.onlinevisacenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
