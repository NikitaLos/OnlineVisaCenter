package com.vironit.onlinevisacenter.controller.user.authorized;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthorizedUserController {
    @GetMapping(value = "/logout")
    public void logOut(HttpSession session)  {
        session.invalidate();
    }
}
