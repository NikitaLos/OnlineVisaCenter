package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.AuthorizationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AuthorizationAspect {

    private UserService userService;

    @Autowired
    public AuthorizationAspect(UserService userService) {
        this.userService = userService;
    }

    @Before("execution(* com.vironit.onlinevisacenter.controller.AdminController.*(..))")
    public void AdminAuthorization() throws AuthorizationException, UserServiceException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        if(session==null||session.getAttribute("user_id") == null){
            throw new AuthorizationException("You need to be logged in as admin");
        }
        User user = userService.getUser((Integer) session.getAttribute("user_id"));
        if (user.getRole()!=Role.ADMIN){
            throw new AuthorizationException("You need to be logged in as admin");
        }
    }

    @Before("execution(* com.vironit.onlinevisacenter.controller.EmployeeController.*(..))")
    public void EmployeeAuthorization() throws AuthorizationException, UserServiceException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        if(session==null||session.getAttribute("user_id") == null){
            throw new AuthorizationException("You need to be logged in as employee");
        }
        User user = userService.getUser((Integer) session.getAttribute("user_id"));
        if (user.getRole()!=Role.EMPLOYEE){
            throw new AuthorizationException("You need to be logged in as employee");
        }
    }

    @Before("execution(* com.vironit.onlinevisacenter.controller.ClientController.*(..))")
    public void ClientAuthorization() throws AuthorizationException, UserServiceException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        if(session==null||session.getAttribute("user_id") == null){
            throw new AuthorizationException("You need to be logged in as client");
        }
        User user = userService.getUser((Integer) session.getAttribute("user_id"));
        if (user.getRole()!=Role.CLIENT){
            throw new AuthorizationException("You need to be logged in as client");
        }
    }

    @Before("execution(* com.vironit.onlinevisacenter.controller.ClientEmployeeController.*(..))")
    public void ClientAndEmployeeAuthorization() throws AuthorizationException, UserServiceException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        if(session==null||session.getAttribute("user_id") == null){
            throw new AuthorizationException("You need to be logged in as client or employee");
        }
        User user = userService.getUser((Integer) session.getAttribute("user_id"));
        if (user.getRole()!=Role.CLIENT&user.getRole()!=Role.EMPLOYEE){
            throw new AuthorizationException("You need to be logged in as client or employee");
        }
    }
}
