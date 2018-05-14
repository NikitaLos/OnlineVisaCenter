package com.vironit.onlinevisacenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String main(){
        return "index";
    }
}
